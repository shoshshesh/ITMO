while (<>) {
    s/\([^)]*\)/\(\)/g;
    print;
}