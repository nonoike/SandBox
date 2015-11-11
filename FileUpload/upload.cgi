#!/usr/bin/perl

##################################################
#
# アップロードされたファイルをシェルに渡すCGI。
#
# [変数設定]
# - SAVE_UPFILE_DIR: アップロードされたファイルの保存先ディレクトリ
# - CALL_EXECUTOR_PATH: バッチの起動シェルのフルパス
# - LOG_DIR: 標準出力の保存先ディレクトリ
#
##################################################

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

require 'download.pl';

# 変数設定
use constant {
    SAVE_UPFILE_DIR    => "/var/www/html/upload/upfile",
    CALL_EXECUTOR_PATH => "/var/www/html/upload/backup.sh",
    LOG_DIR            => "/var/www/html/upload/log",
};
my ($upfile_path);
my ($sec, $min, $hour, $mday, $mon, $year, $wday, $yday, $isdst) = localtime(time);

# 実処理
# print "Content-type: text/plain\n\n"; # デバッグ用
&save_upfile();
&call_executor();

#sleep(10);
#&download_file(${upfile_path});

&create_download_html(${upfile_path});
exit;

##################################################
#
# サブルーチン
#
##################################################

# アップロードされたファイルをサーバー上に保存する
sub save_upfile {
    my $query = new CGI;
    my $fname = basename(${query} -> param('upfile'));
    $upfile_path = SAVE_UPFILE_DIR."/".${fname};

    my $fh = ${query} -> upload('upfile');
    copy (${fh}, ${upfile_path});
    undef $query;
}

# バッチを起動するシェルをバックグラウンドで起動
sub call_executor {
    my $yyyymmdd = sprintf("%04d%02d%02d", ${year} + 1900, ${mon} + 1, ${mday});
    my $log_path = LOG_DIR."/log_${yyyymmdd}.log";
    my $cmd = qq|sh |.CALL_EXECUTOR_PATH.qq| "${upfile_path}" &>> ${log_path} &|;
    my $result = `${cmd}`;
}
