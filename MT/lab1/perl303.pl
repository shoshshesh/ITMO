#!/usr/bin/perl
my @links = ();
my @websites = ();
my $allInputInOneLine = "";

while(<>) {
    $allInputInOneLine = $allInputInOneLine.$_;
}

while ($allInputInOneLine =~ m/<\s*a.*?\bhref\s*=\s*"(\s*([^\"]*)\s*)".*?>/g) {
    push(@links, $1);
}

foreach (@links) {
    /(?<scheme>([^\/:?#]+:)?\/\/)?(?<user>(\w+(:\w+)?@))?(?<nameHost>[^:\/?#]+)(?<namePort>\:\d)?([:\/?#].*)?/;
    my $scheme = $+{scheme};
    my $nameHost = $+{nameHost};
    my $namePort = $+{namePort};
    if(!($scheme =~ /^\s*$/)) {
        push(@websites, $nameHost);
    } elsif (!($namePort =~ /^\s*$/)) {
        push(@websites, $nameHost);
    } else {
        next;
    }
}

my $previousSite = "";
foreach (sort(@websites)) {
    unless (($_ =~ /^\s*$/) || $previousSite eq $_) {
        print "$_\n";
    }
    $previousSite = $_;
}