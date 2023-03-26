#!/usr/bin/perl
use strict;
use warnings FATAL => 'all';
my @lines;
my $firstEmpty = 1;
while (<>) {
    s/^\s+//;
    s/\s+/ /g;
    s/<[^>]+>//g;
    s/\s+$/\n/;
    if (/^\s*$/) {
        if ($firstEmpty == 1) {
            push(@lines, "$_");
            $firstEmpty = 0;
        }
    } else {
        push(@lines, $_);
        $firstEmpty = 1;
    }
}
if ($#lines != -1) {
    unless ($lines[0] =~ /^\s*$/) {
        print($lines[0]);
    }
    for(my $i = 1; $i <= $#lines - 1; $i++){
        if ($lines[$i] =~ /^\s*$/) {
            print("\n");
        } else {
            print($lines[$i]);
        }
    }
    unless ($lines[$#lines] =~ /^\s*$/) {
        print($lines[$#lines]);
    }
}