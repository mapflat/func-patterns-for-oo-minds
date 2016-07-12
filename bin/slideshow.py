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


def start():
    os.system(
        "grip '--title=Functional patterns for object-oriented minds' --wide slides/slides.md 2>&1 > /tmp/grip.out")


def current_branch():
    lines = subprocess.check_output(["git", "status", "--short", "--branch", "--porcelain"]).splitlines()
    return str(lines[0]).split()[1].split(sep="...")[0]


def current_slide():
    return current_branch()[len(SLIDE_PREFIX):]


def slide_index():
    return SLIDES.index(current_slide())


def switch_to_slide(name):
    os.system("git clean --force --quiet -x")
    os.system("git checkout {}{}".format(SLIDE_PREFIX, name))


def move(count):
    switch_to_slide(SLIDES[(slide_index() + count) % len(SLIDES)])


def prev_slide():
    move(-1)


def next_slide():
    move(1)


def main(argv):
    os.chdir(str(Path(argv[0]).parent.parent))
    parser = ArgumentParser(description="Slideshow script")
    args = parser.parse_args(argv)
    {'start': start,
     'move': move,
     'prev': prev_slide,
     'next': next_slide}[args[0]](*args[1:])


if __name__ == '__main__':
    sys.exit(main(sys.argv))
