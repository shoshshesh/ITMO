while (<>) {
    print if /[x-z][[:alnum:]]{5,17}[x-z]/;
}