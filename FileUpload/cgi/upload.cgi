#!/usr/bin/perl

use strict;
use warnings;
use utf8;
use CGI;
use CGI::Carp qw(fatalsToBrowser warningsToBrowser);
use File::Copy;
use File::Basename;

# 入出力にシフトJISを使う場合はこちらを有効化
=pod
binmode STDIN,  ':encoding(cp932)';
binmode STDOUT, ':encoding(cp932)';
binmode STDERR, ':encoding(cp932)';
=cut

# 変数設定
use constant {
    SAVE_UPFILE_DIR    => "/var/www/html/upload/upfile",
};
my $upfile_path = SAVE_UPFILE_DIR."/upfile.csv";  # デフォルト値、処理内で書き換え

# アップロードされたファイルをサーバー上に保存する
my $query = new CGI;
my $fname = basename(${query} -> param('upfile'));
$upfile_path = SAVE_UPFILE_DIR."/".${fname};

my $fh = ${query} -> upload('upfile');
copy (${fh}, ${upfile_path});
undef $query;

# 終了
print "Content-type:text/plain\n\n";
print "save path:".${upfile_path}."\n";
