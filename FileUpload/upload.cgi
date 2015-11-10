#!/usr/bin/perl

##################################################
#
# アップロードされたファイルをシェルに渡すCGI。
#
# [変数設定]
# - SAVE_UPFILE_DIR: アップロードされたファイルの保存先ディレクトリ
# - CALL_EXECUTOR_PATH: バッチの起動シェルのフルパス
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
print "Content-type: text/html\n\n";
&save_upfile;
&call_executor;


my $url = &to_url(${upfile_path});

# 終了
print "Content-type:text/plain\n\n";
print "save path:".${upfile_path}."\n";
print "executor path:".CALL_EXECUTOR_PATH."\n";

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

# バッチを起動するシェルを呼び出す
sub call_executor {
    my $yyyymmdd = sprintf("%04d%02d%02d", ${year} + 1900, ${mon} + 1, ${mday});
    my $log_path = LOG_DIR."/log_${yyyymmdd}.log";
    my $cmd = qq|sh |.CALL_EXECUTOR_PATH.qq| "${upfile_path}" &>> ${log_path} &|;
    my $result = `${cmd}`;
}

# パスをURL表記に変換
sub to_url {
    ($path) = @_;
    my $regex = "/var/www/html";
    my $replacement = "http://localhost:8080";
    (my $url = ${path}) =~ s/${regex}/${replacement}/g;
    # print ${url};
    return ${url};
}

# ファイルのダウンロードサンプル
# sub download_file {
#     print "Content-Type: application/force-download\n";
#     print qq|Content-Disposition: attachment; filename="|.basename(${download_path}).qq|"\n\n|;
#     open(DLFILE, ${download_path}) or die "cannot open '$!'";
#     print for <DLFILE>;
#     close(DLFILE);
# }
