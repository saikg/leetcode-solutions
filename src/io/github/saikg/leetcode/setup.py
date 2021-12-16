import sys
import os


def get_package_name(problem_id):
    return


def setup_template(fp, problem_id):
    fp.write(f"package io.github.saikg.leetcode.s{problem_id};\n")
    fp.write('\n')
    fp.write('public class Solution {\n')
    fp.write('\n}')


def main():
    base_dir = "/Users/saikiran/Programming/Leetcode/src/io/github/saikg/leetcode"
    problem_id = sys.argv[1]
    dir = base_dir + "/s" + problem_id
    try:
        os.mkdir(dir)
    except:
        print("Problem directory already exists")
        return
    os.chdir(dir)
    with open("Solution.java", "w+") as file:
        setup_template(file, problem_id)


if __name__ == '__main__':
    main()
