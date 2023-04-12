# Tugas Kecil 3 - Strategi Algoritma
Tugas Kecil 3 IF221
##Table of Contents
- [Deskripsi Umum](#deskripsi-umum)
- [Installation Requirement](#installation-requirement)
- [How To Run!](#how-to-run)

## Deskripsi Umum
Algoritma UCS (Uniform cost search) dan A* (atau A star) dapat digunakan untuk menentukan lintasan terpendek dari suatu titik ke titik lain. Pada tugas kecil 3 ini, anda diminta menentukan lintasan terpendek berdasarkan peta Google Map jalan-jalan di kota Bandung. Dari ruas-ruas jalan di peta dibentuk graf. Simpul menyatakan persilangan jalan (simpang 3, 4 atau 5) atau ujung jalan. Asumsikan jalan dapat dilalui dari dua arah. Bobot graf menyatakan jarak (m atau km) antar simpul. Jarak antara dua simpul dapat dihitung dari koordinat kedua simpul menggunakan rumus jarak Euclidean (berdasarkan koordinat) atau dapat menggunakan ruler di Google Map, atau cara lainnya yang disediakan oleh Google Map.

![Screenshot 2023-04-12 230221](https://user-images.githubusercontent.com/103443120/231515910-dfbd3f67-a705-4b66-a98c-a60836316cee.jpg)

Langkah pertama di dalam program ini adalah membuat graf yang merepresentasikan peta (di area tertentu, misalnya di sekitar Bandung Utara/Dago). Berdasarkan graf yang dibentuk, lalu program menerima input simpul asal dan simpul tujuan, lalu menentukan lintasan terpendek antara keduanya menggunakan algoritma UCS dan A*. Lintasan terpendek dapat ditampilkan pada peta/graf (misalnya jalan-jalan yang menyatakan lintasan terpendek diberi warna merah). Nilai heuristik yang dipakai adalah jarak garis lurus dari suatu titik ke tujuan.

## Kebutuhan Menjalankan Program
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

## How To Run
1. Clone/Download repositori
2. Buka terminal pada lokasi repositori
3. Pada terminal salin/ketik teks dibawah ini:
```
java -jar "Tucil3_Maps.jar"
```
4. Input lokasi menggunakan contoh files di folder [test](./test/) untuk memasukkan simpul-simpulnya.
5. Pilih simpul start dan simpul end yang sudah ditampilkan
7. Pilih algoritma (UCS atau AStar)
8. Anda dapat memilih kembali start dan end kembali atau dapat juga memasukkan file simpul-simpul yang lainnya.
9. Selamat menikmati

## Pembuat
- [13521012: Haikal Ardzi Shofiyyurrohman](https://github.com/AlphaThrone)
- [13521028: Muhammad Zulfiansyah Bayu Pratama](https://github.com/zulfiansyah404)
