#  UI-тесты hh.ru (5 шт.)

##  Локальный запуск

```bash
./gradlew clean test -Dtags=hh
```

По умолчанию используется **Chrome 128**, разрешение **1920x1080**, без **Selenoid**.

---

###  Запуск через Selenoid

```bash
./gradlew clean test -Dtags=hh \
  -DurlSelenide=selenoid.autotests.cloud \
  -DremoteLogin="$REMOTE_LOGIN" \
  -DremotePassword="$REMOTE_PASSWORD" \
  -DbrowserAndVersion=chrome_128 \
  -DbrowserSize=1920x1080
```

---

##  Jenkins (матрица браузеров)

```bash
#!/bin/bash
set -euo pipefail

rm -rf build/allure-results build/allure-results-iters allure-report || true
mkdir -p build/allure-results build/allure-results-iters

chmod +x gradlew
./gradlew clean

for BROWSER in chrome_128 firefox_125; do
  for SIZE in 1920x1080; do
    ITER_DIR="build/allure-results-iters/${BROWSER}_${SIZE}"
    echo "Run: $BROWSER $SIZE"

    ./gradlew test --continue -Dtags=hh \
      -DbrowserAndVersion="$BROWSER" \
      -DbrowserSize="$SIZE" \
      -DurlSelenide="selenoid.autotests.cloud" \
      -DremoteLogin="$REMOTE_LOGIN" \
      -DremotePassword="$REMOTE_PASSWORD" \
      -Dallure.results.directory="$ITER_DIR"

    mkdir -p build/allure-results
    cp -r "$ITER_DIR"/* build/allure-results/ || true
  done
done

echo "Done"
```

---

##  Allure Report

Отчёт подхватывается из `build/allure-results` (**плагин Jenkins**)  
и открывается как `build/reports/allure-report`.

---

##  Технологии

| Технология | Версия |
|-------------|--------|
| ☕ Java | 17     |
| 🧱 Gradle | 8      |
| 🧪 JUnit | 5      |
| 🌿 Selenide | 7      |
| 📊 Allure | 2      |
| ☁️ Selenoid | ?      |
| ⚙️ Jenkins | ?      |

---

##  Примечание

Сайт **hh.ru** периодически отдаёт капчу — возможны падения на CI.  
Результаты разных браузеров **не затираются**  
(уникальные `historyId` и `environment.properties`).

---

 *Проект создан в учебных целях. Автоматизация выполнена в рамках курса по UI-тестированию с использованием Java, Gradle и Selenide.*
