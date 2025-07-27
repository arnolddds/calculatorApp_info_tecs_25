# Calculator

**Native Calculator** — это Android-приложение-калькулятор, в котором все основные математические операции реализованы через нативный код на C++ с использованием JNI.

Это тестовое задание выполнено в полном объёме, с добавлением дополнительных возможностей:  
🔹 Экран истории расчетов  
🔹 Архитектура **MVVM + Clean Architecture**  
🔹 Внедрение зависимостей через **Hilt**

---

##  Внешность
### Темная тема

Главный экран | Экран истории вычислений | Обработка ошибок  
:--:|:--:|:--:  
<img src="screenshots/main_dark_screen.png" width="70%"> | <img src="screenshots/history_dark_screen.png" width="70%"> | <img src="screenshots/error_dark_screen.png" width="70%">

### Светлая тема

Главный экран | Экран истории вычислений | Обработка ошибок  
:--:|:--:|:--:  
<img src="screenshots/light_main_screen.png" width="70%"> | <img src="screenshots/history_light_screen.png" width="70%"> | <img src="screenshots/error_light_screen.png" width="70%">

### Упрощенная версия - ветка simple_calculator

Главный экран| Обработка ошибок  
:--:|:--:  
<img src="screenshots/simple_main_screen.png" width="70%"> | <img src="screenshots/error_simple_main_screen.png" width="70%">

---

## ✅ Требования

### 🔹 Основной функционал

- [x] Поля ввода чисел
- [x] Кнопки для операций: `+`, `-`, `×`, `÷`
- [x] Вывод результата (TextView)
- [x] Все вычисления производятся в **нативном коде (C++) через JNI**
- [x] Возврат результата из C++ в Kotlin и отображение на экране

### 🔸 Дополнительные возможности

- [x] Обработка ошибок:
  - Деление на ноль
  - Пустые или некорректные поля
- [x] Логгирование операций в C++ через `__android_log_print`
- [x] CMake-сборка нативной библиотеки
- [x] Экран с историей всех вычислений
- [x] Unit-тесты для проверки нативных методов

---

## Используемые технологии

- **Kotlin**
- **C++** (через **JNI**)
- **CMake** — сборка нативного кода
- **Hilt** — внедрение зависимостей
- **ViewModel / StateFlow** — управление состоянием
- **MVVM + Clean Architecture**
- **Jetpack Navigation** — навигация между экранами
- **Room** — хранение истории вычислений
- **JUnit** — модульные тесты для JNI

---
##  Запуск проекта

1. Клонировать репозиторий:
```bash
git clone https://github.com/arnolddds/calculatorApp_info_tecs_25.git
```
##  Контакты
Telegram @arnoldsss

