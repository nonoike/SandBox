#!/bin/sh

##################################################
#
# 引数で渡されたファイルのバックアップを作成する。
#
##################################################

ORIGINAL_PATH=$1
WITHOUT_EXTENSION=${ORIGINAL_PATH%.*}
EXTENSION=${ORIGINAL_PATH##*.}
BACKUP_PATH=${WITHOUT_EXTENSION}"_bk."${EXTENSION}

cp ${ORIGINAL_PATH} "${BACKUP_PATH}"
