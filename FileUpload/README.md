FileUpload
==========

静的HTMLからファイルをアップロードし、そのファイルに対してバッチ処理を行う。  
perl v5.10.1で動作確認。

### 環境構築
cent os 6.5での環境構築。

1. apacheのインストール  
  `yum -y install httpd`  
1. CGIのインストール  
  `yum -y install perl-CGI`  
1. 各種ディレクトリを作成する  
  - cgiを配置するディレクトリ  
    `mkdir /var/www/cgi-bin/`  
  - アップロードファイルを保存するディレクトリ
    `mkdir /var/www/html/upload/upfile`  
  - ログを出力するディレクトリ
    `mkdir /var/www/html/upload/log`  
1. CGIが動くようにapacheの設定を変更  
  `vi /etc/httpd/conf/httpd.conf`  
    - cgiを配置するディレクトリの設定に"Options +ExecCGI"を追加  
    - "AddHandler cgi-script .cgi .pl"を有効化
1. apacheの設定ファイルの再読み込み  
  `service httpd reload`
1. 権限の追加  
  `chmod 755 <cgiを配置するディレクトリ>`  
  `chmod 755 <cgiファイル>`  
  `chmod 777 <アップロードファイルを保存するディレクトリ>`  
  `chmod 777 <ログファイルを出力するディレクトリ>`  
  `chmod 777 <シェルスクリプト>`  
  `chmod 777 <シェルスクリプトの入出力(ログなど)ディレクトリ>`  

### 注意
- アップしたファイルは常に存在し続けているので、事実上ajaxの処理はシェルが終わるまで待機せず、即ダウンロードが可能な状態となる。
  そのためコードサンプルとしてのみ使用すること。  
  TODOとし、余裕があったら修正。
