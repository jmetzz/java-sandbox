See Effective Java 2nd Edition, Item 28:

<kbd>P</kbd><kbd>e</kbd><kbd>C</kbd><kbd>s</kbd>

<kbd>P</kbd>roducer <kbd>e</kbd>xtends, <kbd>C</kbd>onsumer <kbd>s</kbd>uper

If your parameter is a producer, it should be `<? extends T>`, if it's a consumer it has to be `<? super T>`.