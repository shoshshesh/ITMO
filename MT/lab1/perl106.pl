while (<>) {
    print if /(^([1-9][0-9]*|0)(\W))|(\W([1-9][0-9]*|0)\W)|(\W([1-9][0-9]*|0)$)/;
}