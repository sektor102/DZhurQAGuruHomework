# UI-тесты hh.ru (5 шт.)

## Запуск локально
./gradlew clean test -Dtags=hh
# По умолчанию: chrome_128, 1920x1080. Без urlSelenide запускается локально.

# Опционально через удалённый Selenoid:
# ./gradlew clean test \
#   -Dtags=hh \
#   -DurlSelenide=selenoid.autotests.cloud \
#   -DremoteLogin=<login> -DremotePassword=<pass> \
#   -DbrowserAndVersion=chrome_128 \
#   -DbrowserSize=1920x1080

## Запуск в Jenkins (матрица браузеров)
# Один clean, прогон по матрице, результаты не затираются — пишем в отдельные папки и склеиваем.
#!/bin/bash
set -euo pipefail
rm -rf build/allure-results build/allure-results-iters allure-report || true
mkdir -p build/allure-results build/allure-results-iters
chmod +x gradlew

./gradlew clean
urlSelenide="selenoid.autotests.cloud"

for BROWSER in chrome_128 firefox_125; do
  for SIZE in 1920x1080; do
    ITER_DIR="build/allure-results-iters/${BROWSER}_${SIZE}"
    ./gradlew test --continue \
      -Dtags=hh \
      -DbrowserAndVersion="$BROWSER" \
      -DbrowserSize="$SIZE" \
      -DurlSelenide="$urlSelenide" \
      -DremoteLogin="$REMOTE_LOGIN" \
      -DremotePassword="$REMOTE_PASSWORD" \
      -Dallure.results.directory="$ITER_DIR"
    cp -r "$ITER_DIR"/* build/allure-results/ || true
  done
done

## Allure
# Отчёт собирается плагином Jenkins из build/allure-results в build/reports/allure-report

## Технологии
Java 17, Gradle 8; JUnit 5, Selenide; Selenoid; Allure; Jenkins.

## Примечание
hh.ru может отдавать капчу — прогоны иногда падают. Интеграции (Selenoid, Jenkins, Allure) настроены; результаты разных браузеров не затираются (уникальный historyId, склейка директорий).
