#!/bin/bash
PROJECT_DIR="/Users/xworui/scala/lab1/scala1"

cd "$PROJECT_DIR" || { echo "Ошибка: директория не найдена"; exit 1; }

echo "Сборка и запуск Scala-программы..."
sbt run
