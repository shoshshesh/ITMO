while (<>) {
    print if /z[[:alnum:]]{3}z/;
}