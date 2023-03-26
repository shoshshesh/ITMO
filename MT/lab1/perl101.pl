while (<>) {
    print if /cat([a-z]|[A-Z]|[:digit:]|\W)*cat/;
}