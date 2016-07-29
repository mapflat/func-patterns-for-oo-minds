#! /usr/bin/env python3

import os
import sys
from argparse import ArgumentParser

from pathlib import Path

import subprocess


SLIDES = [
    "title",
    "bio",
    "intro",
    # More here
]

SLIDE_PREFIX = 'slide-'


def run_cmd(cmd):
    print("> {}".format(cmd))
    os.system(cmd)


def start():
    run_cmd(
        "grip '--title=Functional patterns for object-oriented minds' --wide slides/slides.md 2>&1 > /tmp/grip.out")


def current_branch():
    lines = subprocess.check_output(["git", "status", "--short", "--branch", "--porcelain"]).splitlines()
    return str(lines[0]).split()[1].split(sep="...")[0]


def current_slide():
    return current_branch()[len(SLIDE_PREFIX):]


def slide_index():
    return SLIDES.index(current_slide())


def git_clean():
    run_cmd("git clean --force --quiet -x")


def switch_to_slide(name):
    git_clean()
    run_cmd("git checkout {}{}".format(SLIDE_PREFIX, name))


def move(count):
    switch_to_slide(SLIDES[(slide_index() + count) % len(SLIDES)])


def prev_slide():
    move(-1)


def next_slide():
    move(1)


def slide_branches():
    return [b for b in subprocess.check_output(["git", "branch", "--list"]).split()
            if b.startswith(SLIDE_PREFIX)]


def rebase():
    git_clean()
    for slide in slide_branches():
        run_cmd("git rebase {}".format(slide))


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
