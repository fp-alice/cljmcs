# cljmcs - The Clojure Minecraft server tool

## Installation

Clone the repository with `git clone`, run `lein bin` in the root directory, and finally run `cp target/default/cljmcs-[version] ~/.local/bin/cljmcs`.
If you don't want the binary, you can also build the jar with `lein uberjar` or download it from the releases.

## Usage

Run `cljmcs help` for usage info.

    cljmcs: The clojure minecraft server tool
    
    Usage: cljmcs <action> [args]

    Actions:
      download [release/snapshot, version]    Downloads minecraft server jar & creates run script
      list     [release/snapshot]             Lists all releases or snapshots
      help                                    Prints this message

## Examples

    $ cljmcs download release 1.13.2
    $ cljmcs download snapshot 19w06a
    $ cljmcs list snapshot
    $ cljmcs help

## License

Copyright © 2019 

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
