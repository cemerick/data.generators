clojure.data.generators [[FORK]]
========================================

**WIP, hasn't been published yet**

Portable Clojure/ClojureScript library for generating random data.

This is a fork of the excellent
[data.generators](https://github.com/clojure/data.generators) library, made to
be portably used from ClojureScript as well (/ht
[cljx](https://github.com/lynaghk/cljx)).  The user-facing API is exactly the
same, _except_ for the following missing bits from the ClojureScript side:

* `clojure.data.generators/ratio`
* All of the primitive `-array` generators (a TODO for the ClojureScript side
  would be to add support for generating JavaScript typed arrays, e.g.
  `Int32Array` and such)
* `clojure.data.generators/bigint` and `clojure.data.generators/bigdec`;
  investigating adding support for these in ClojureScript by producing
  [bignumbers](https://github.com/MikeMcl/bignumber.js)

This fork will track data.generators proper.  `[com.cemerick/data.generators]`
version numbers will track data.generators version numbers as well, using a
suffixed classifier (e.g. `0.1.2` turns into `0.1.2-1`) to indicate changes
here.  Eventually, it would be nice to get this work folded in upstream, but the
nuclear-bleeding edge nature of portable Clojure[Script] API development and
current toolchain restrictions of Clojure contrib mean that keeping it separate
for now makes sense.

Releases and Dependency Information
========================================

* [All Released Versions](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.cemerick%22%20AND%20a%3A%22data.generators%22)

* [Development Snapshot Versions](https://oss.sonatype.org/index.html#nexus-search;gav~com.cemerick~data.generators~~~)

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

```clojure
    [com.cemerick/data.generators "0.1.2-1"]
```

[Maven](http://maven.apache.org/) dependency information:

```xml
    <dependency>
      <groupId>com.cemerick</groupId>
      <artifactId>data.generators</artifactId>
      <version>0.1.2-1</version>
    </dependency>
```

## Need Help?

Ping `cemerick` on freenode irc or
[twitter](http://twitter.com/cemerick) if you have questions or would
like to contribute patches.

Copyright and License
========================================
Copyright ©2013 [Chas Emerick](http://cemerick.com) and other contributors.

Copyright (c) 2012 Rich Hickey. All rights reserved.  The use and distribution terms for this software are covered by the Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be found in the file epl-v10.html at the root of this distribution. By using this software in any fashion, you are agreeing to be bound bythe terms of this license.  You must not remove this notice, or any other, from this software.
