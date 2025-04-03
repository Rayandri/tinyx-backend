#!/bin/sh

# Dossier de sortie (à adapter si besoin)
DUMP_DIR="/data/dump/$(date +%F-%H%M)"
mkdir -p "$DUMP_DIR"

mongodump --uri="mongodb://localhost:27017/tinyx" --out="$DUMP_DIR"

echo "[INFO] Dump completed: $DUMP_DIR"
