#!/bin/bash
if [ -z "$1" ]; then
  echo "Использование: $0 <имя_проекта>"
  exit 1
fi

PROJECT_NAME=$1
mkdir "$PROJECT_NAME"
cd "$PROJECT_NAME" || exit

# Создаём структуру проекта
mkdir -p src/main/scala src/test/scala

# Файл build.sbt
cat <<EOF > build.sbt
name := "$PROJECT_NAME"
version := "0.1"
scalaVersion := "3.3.1"
EOF

# Пример программы
cat <<EOF > src/main/scala/Main.scala
@main def hello(): Unit =
  println("Hello from Scala project '$PROJECT_NAME'!")
EOF

echo "Проект '$PROJECT_NAME' успешно создан!"
echo "Чтобы запустить его, введите:"
echo "  cd $PROJECT_NAME && sbt run"

