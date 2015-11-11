#!/usr/bin/perl

##################################################
#
# ダウンロードに関するサブルーチンを提供するライブラリ。
#
# [変数設定]
# - root_url: URL表記のルート
# - root_path: ディレクトリパス表記のルート
#
##################################################

use strict;
use warnings;
use utf8;
use File::Basename;

my $root_url ="http://localhost:8080";
my $root_path ="/var/www/html";

# ダウンロード用ページを作成
# $_[0]:ダウンロードファイル(URL、ディレクトリパスどちらでも可)
sub create_download_html {
    my ($download_uri) = @_;
    $download_uri = (${download_uri} =~ /^http/) ? ${$download_uri} : &to_url(${download_uri});
    print "Content-type: text/html\n\n";
    print <<"HTML";
        <HTML>
        <body>
        <div id="status">
            ファイルの作成中です。完了後、DLリンクが表示されます。
        </div>
        <script type="text/javascript">
        var xmlHttp, intervarId;
        window.onload = function () {
            intervarId = setInterval("existsFile()", 1000);
        }

        function existsFile() {
            xmlHttp = new XMLHttpRequest();
            xmlHttp.onreadystatechange = checkStatus;
            xmlHttp.open("GET", "${download_uri}", true);
            xmlHttp.send(null);
        }

        function checkStatus() {
            var result = "ファイルの作成が完了しました。下記からDLができます。"
                         + "<p><a href=\\"${download_uri}\\">ダウンロード</a></p>";
            console.log("readyState: " + xmlHttp.readyState + ", status: " + xmlHttp.status);
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                clearInterval(intervarId);
                document.getElementById("status").innerHTML = result;
            }
        }
        </script>
        </body>
        </HTML>
HTML
}

# ファイルのダウンロード
# $_[0]:ダウンロードファイル(URL、ディレクトリパスどちらでも可)
sub download_file {
    my ($download_path) = @_;
    $download_path = (${download_path} =~ /^http/) ? &to_path(${download_path}) : ${download_path};
    print "Content-Type: application/force-download\n";
    print qq|Content-Disposition: attachment; filename="|.basename(${download_path}).qq|"\n\n|;
    open(DLFILE, ${download_path}) or die "cannot open '$!'";
    print for <DLFILE>;
    close(DLFILE);
}

# ディレクトリパスをURL表記に変換
# $_[0]:ダウンロードファイルのフルパス
sub to_url {
    my ($path) = @_;
    (my $url = ${path}) =~ s/^${root_path}/${root_url}/i;
    # print ${url};
    return ${url};
}

# URLをディレクトリパス表記に変換
# $_[0]:ダウンロードファイルのURL
sub to_path {
    my ($url) = @_;
    (my $path = ${url}) =~ s/^${root_url}/${root_path}/i;
    # print ${path};
    return ${path};
}

1;
