#!/bin/sh

##################################################
#
# 引数で渡されたファイルのバックアップを作成する。
#
##################################################

ORIGINAL_PATH=$1
DATE=`date '+%y/%m/%d'`
DATETIME=`date '+%y/%m/%d %H:%M:%S'`
WITHOUT_EXTENSION=${ORIGINAL_PATH%.*}
EXTENSION=${ORIGINAL_PATH##*.}
BACKUP_PATH=${WITHOUT_EXTENSION}"_${DATE}."${EXTENSION}

sleep 5s;
cp ${ORIGINAL_PATH} "${BACKUP_PATH}"
echo "${DATETIME} ORIGINAL_PATH: ${ORIGINAL_PATH}"
echo "${DATETIME} BACKUP_PATH: ${BACKUP_PATH}"
