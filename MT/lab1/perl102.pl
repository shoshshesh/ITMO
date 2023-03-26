while (<>) {
    print if /(^(\W)*cat(\W)([:alnum:]|\W)*)|(([:alnum:]|\W)*(\W)cat(\W)([:alnum:]|\W)*)|(([:alnum:]|\W)*(\W)cat(\W)*$)/;
}