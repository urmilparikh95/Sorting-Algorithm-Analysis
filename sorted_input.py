#! /usr/bin/env python3

import sys

def usage(program_name):
    print("Usage", program_name, "N")
    print(" prints N sorted integers")
    
def main():
    if len(sys.argv) != 2:
        usage(sys.argv[0])
        sys.exit()
    N = int(sys.argv[1])
    print("n", N)
    for i in range(N):
        print(i+1)

main()
    
#  [Last modified: 2018 01 28 at 16:48:15 GMT]

