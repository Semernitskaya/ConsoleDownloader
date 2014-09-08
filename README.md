ConsoleDownloader
=================
Для старта приложения выполнить gradle run. Аргументы командной строки задаются в build.gradle
 run {
     args '-n'
     args '5'
     args '-l'
     args '20000'
     args '-o'
     args 'output'
     args '-f'
     args 'links.txt'
 }
Для тестирования использовался файл ConsoleDownloader\links.txt

Для сборки выполнить gradle clean build.
Созданный jar-файл ConsoleDownloader\build\libs\ConsoleDownloader-1.0.jar запускать из командной строки
java -jar ConsoleDownloader-1.0.jar -n 5 -l 2000k -o output_folder -f links.txt

Уровень логирования задаётся в log4j.properties. По умолчанию DEBUG.