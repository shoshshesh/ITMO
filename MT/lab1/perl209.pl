while (<>) {
    s/\([^)]*\)/\(\)/g;
    print;
}