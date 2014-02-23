# transpiler

A Clojure library designed to convert from X86 to Y86. right now, all it does is convert the first procedure of the input into a cons tree suitable for pattern matching later

#notes
https://github.com/UTTuring/x86-y86-transpiler is probably a better idea, but I'm too lazy to learn how to lex

## Usage
Use the standalone jar in target/transpiler-something-something-stanpshot-standalone.jar

Takes one command line option, the path to a .S assembly file. NOTE A FEW THINGS:
  1. use gcc, not clang. outputs nicer assembly
  2. compile with following flags: O1, "-fno-leading-underscore", and whatever else you think won't screw things up.

## Notes
regexes are shitty, this probably is never going to work super well
also this is my first attempt at clojure, so yay

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
