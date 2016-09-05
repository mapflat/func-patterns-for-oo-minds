#! /usr/bin/env python3

import os
import shutil
import sys
from argparse import ArgumentParser

from pathlib import Path

import subprocess

SLIDES = [
    "title",
    "bio",
    "oo-dominating",
    "functional-gems",
    "patterns",
    "exceptions",
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


def slide_index():
    return SLIDES.index(current_slide())


current_path = Path("slides/current")


def current_slide():
    if current_path.exists():
        return current_path.read_text().strip()
    return None


def set_current(slide):
    current_path.write_text(slide + "\n")


def source_paths():
    return [Path(
        "src/{}/{}/com/mapflat/presentations/funcpatterns/{}.{}".format(scope, lang, name, lang))
            for scope, lang, name in (("main", "java", "JavaSlide"), ("main", "java", "JavaDeps"),
                                      ("test", "java", "JavaSlideTest"),
                                      ("main", "scala", "ScalaSlide"),
                                      ("main", "scala", "ScalaDeps"),
                                      ("test", "scala", "ScalaSlideTest"))]


write_mode = True


def save_slide():
    current = current_slide()
    if write_mode:
        for path in source_paths():
            dst_dir = Path("slides", current)
            dst_dir.mkdir(exist_ok=True)
            if path.exists():
                # print("Saving {} to {}".format(path, dst_dir))
                shutil.copy2(str(path), str(dst_dir))
        print("Saved slide {}".format(current))
    else:
        print("Read-only mode, not saving")


def switch_to_slide(slide):
    for path in source_paths():
        path.parent.mkdir(parents=True, exist_ok=True)
        src_path = Path("slides", slide, path.name)
        if src_path.exists():
            # print("Copying {} to {}".format(src_path, path))
            shutil.copy2(str(src_path), str(path))
    set_current(slide)
    print("Switched to slide {}".format(slide))


def move(count):
    current = current_slide()
    if current is None:
        if count > 0:
            switch_to_slide(SLIDES[count - 1])
        else:
            switch_to_slide(SLIDES[count])
    else:
        save_slide()
        switch_to_slide(SLIDES[(slide_index() + count) % len(SLIDES)])


def prev_slide():
    move(-1)


def next_slide():
    move(1)


def slide_branches():
    return [b for b in cmd_out(["git", "branch", "--list"]).split()
            if b.startswith(SLIDE_PREFIX)]


def main(argv):
    os.chdir(str(Path(argv[0]).parent.parent))
    parser = ArgumentParser(description="Slideshow script")
    parser.add_argument('operations', nargs='+', help="Operations to perform")
    args = parser.parse_args(argv[1:])
    commands = {
        'move': move,
        'next': next_slide,
        'prev': prev_slide,
        'save': save_slide,
    }
    return commands[args.operations[0]](*args.operations[1:])


if __name__ == '__main__':
    sys.exit(main(sys.argv) or 0)
