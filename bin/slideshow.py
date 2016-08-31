#! /usr/bin/env python3

import os
import sys
from argparse import ArgumentParser

from pathlib import Path

import subprocess


SLIDES = [
    "title",
    "bio",
    "func-rising",
    "func-rising-1982",
    "oo-dominating",
    "functional-gems",
    # Rouz, add here

    "error-mgmt",
    "option",
    "option-backfire",
    "either",
    "disjunctions",
    "util-try",
    "validations",
    "validations-nel",
    "lenses",
    "json-path",
    "argonaut",
    "play-json",
    "monocle",
    "monocle-useful",
    "lenses-spotify",
    "lenses-test-lessons",
    "type-lenses",
    "roundup",
    "references",
]

SLIDE_PREFIX = 'slide-'


def run_cmd(cmd):
    print("> {}".format(cmd))
    os.system(cmd)

def cmd_out(cmd):
    return subprocess.check_output(cmd).decode()


def start():
    run_cmd(
        "grip '--title=Functional patterns for object-oriented minds' --wide slides/slide.md 2>&1 > /tmp/grip.out")


def current_branch():
    lines = cmd_out(["git", "status", "--short", "--branch", "--porcelain"]).splitlines()
    return lines[0].split()[1].split(sep="...")[0]


def current_slide():
    return current_branch()[len(SLIDE_PREFIX):]


def slide_index():
    return SLIDES.index(current_slide())


def clean_workspace():
    run_cmd("git checkout .")
    run_cmd("git clean --force --quiet -x -e .idea -e '*.iml'")


def switch_to_slide(name):
    clean_workspace()
    run_cmd("git checkout {}{}".format(SLIDE_PREFIX, name))


def move(count):
    if current_branch() == "master":
        if count > 0:
            switch_to_slide(SLIDES[count - 1])
        else:
            switch_to_slide(SLIDES[count])
    else:
        switch_to_slide(SLIDES[(slide_index() + count) % len(SLIDES)])


def prev_slide():
    move(-1)


def next_slide():
    move(1)


def slide_branches():
    return [b for b in cmd_out(["git", "branch", "--list"]).split()
            if b.startswith(SLIDE_PREFIX)]


def rebase():
    clean_workspace()
    current = current_branch()
    for slide in slide_branches():
        run_cmd("git checkout {}".format(slide))
        run_cmd("git rebase master".format(slide))
    run_cmd("git checkout {}".format(current))



def main(argv):
    os.chdir(str(Path(argv[0]).parent.parent))
    parser = ArgumentParser(description="Slideshow script")
    parser.add_argument('operations', nargs='+', help="Operations to perform")
    args = parser.parse_args(argv[1:])
    commands = {
        'move': move,
        'next': next_slide,
        'prev': prev_slide,
        'rebase': rebase,
        'start': start,
    }
    return commands[args.operations[0]](*args.operations[1:])


if __name__ == '__main__':
    sys.exit(main(sys.argv) or 0)
