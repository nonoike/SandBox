FileUpload
==========

静的HTMLからファイルをアップロードし、そのファイルに対してバッチ処理を行う。
perl v5.10.1で動作確認。

### 環境構築
cent os 6.5での環境構築。

1. apacheのインストール  
  `yum -y install httpd`
1. CGIが動くようにapacheの設定を変更
  `vi /etc/httpd/conf/httpd.conf`
    - cgiを配置するフォルダの設定に"Options +ExecCGI"を追加
    - "AddHandler cgi-script .cgi"を有効化
1. 権限の追加
  `chmod 755 <cgiを配置するフォルダ>`
  `chmod 755 <cgiファイル>`
1. CGIのインストール
  `yum -y install perl-CGI`
