# Tugas Besar 2 - IF2210 Pemrograman Berorientasi Objek
[![Gradle Build](https://github.com/Putriliza/IF2210_TB_03_02/actions/workflows/gradle.yml/badge.svg)](https://github.com/Putriliza/IF2210_TB_03_02/actions/workflows/gradle.yml)

## Link Diagram
https://lucid.app/lucidchart/85a782f0-3957-4a3b-9fcd-e680579d99fe/edit?invitationId=inv_5961b463-df58-4068-8b49-2401029a31f1 

## General Info
Saat-saatnya Mobita asik dalam petualangannya di permainan Tambang-Kerajinan, Mobita menemukan sebuah guide di website reddot.com untuk membuat portal menuju Aether Realm. Namun setelah mencoba membuatnya, ternyata Mobita tertipu oleh sebuah hoax!. Doraemonangis beneran nangis melihat Mobita kecewa. Tidak ingin melihatnya bersedih, Doraemonangis mengajak Anda untuk mengimplementasi Aether Realm ke dalam permainan Tambang-Kerajinan. Doraemonangis membayangkan Aether Realm berisi monster-monster yang ganas dari seluruh realm Tambang-Kerajinan dan Mobita sebagai pemain harus melawan mereka saat memasukinya.

Anda akan membantu Doraemonangis untuk membuat game kartu turn based untuk 2 pemain. Pemain bermain secara bergantian pada 1 layar yang sama. Tujuan dari game ini adalah menghabiskan health points (HP) musuh. HP dapat berkurang apabila terkena serangan dari kartu karakter yang diletakkan di board.

## Informasi
Data                | Isi
----                | ---
Tahun Ajaran        | 2022
Tanggal Mulai       | 01 April 2022
Tanggal Selesai     | 29 April 2022
Tanggal Pengumpulan | 28 - 29 April 2022
Kelas               | 3
Dosen               | Habibur Muhaimin ST,M.Sc.

## Setup
0. PC dengan OS Windows. WSL tidak bisa, Ubuntu tidak dicoba sehingga do at your own risk.
1. Install JDK 17
2. Masuk ke directory tempat folder di clone
3. Untuk menjalankan program, `.\gradlew run`
4. Untuk menjalankan testing, `.\gradlew test`
5. Selamat bermain !!!

## Spesifikasi
**Mekanisme**
Kumpulan Mekanisme                | Status
-----------                       | ------
GUI                               | :green_square:
Testing                           | :green_square:

**Kelas**
Kumpulan Kelas                                 | Status
-----------                                    | ------
Kartu                                          | :green_square:
KartuKarakter                                  | :green_square:
KartuSpell                                     | :green_square:
KartuSpellLvl                                  | :green_square:
KartuSpellMorph                                | :green_square:
KartuSpellSwap                                 | :green_square:
KartuSpellPotion                               | :green_square:
Game                                           | :green_square:
Player                                         | :green_square:
Reference                                      | :green_square:
App                                            | :green_square:

**Testing**
Kumpulan Testing                                 | Status
-----------                                       | ------
CharacterTest                                          | :green_square:
GameTest                                 | :green_square:
PlayerTest                                    | :green_square:
ReferenceTest                                 | :green_square:
SpellTest                               | :green_square:
                                       
## Laporan
**Status : Done** :green_square: \
**3 / 3**
Bab                     | Status
---                     | ------
Diagram Kelas           | :green_square:
Penerapan Konsep OOP    | :green_square:
Pembagian Kerja         | :green_square:

## Pembagian Tugas
NAMA                              | Tugas
-----------                       | ------
Marcellus Michael Herman K        | Mana, Apply Spell, Attack, Debugging
Putri Nurhaliza                   | KartuSpell, Lvl, Potion dan implementasi, GUI: Bind deck, mana, hand card, dan Hover Card Details, Testing spell, character
Adelline Kania Setiyawan          | Player dan implementasi, GUI: Bind Turn, Health Progress, Phase, Membuat logic pemindahan kartu dari hand ke board pada GUI
Dimas Shidqi Parikesit            | CSetup gradle Membuat class Game, Reference Testing class Game, Reference Membuat logic attack, Membuat logic spell, Membuat converter mana to exp dan GUI : next phase bind, Debugging spell, attack, GUI
Rizky Akbar Asmaran               | Membuat kelas Kartu, Membuat kelas KartuKarakter, Laporan
Vito Ghifari                      | Membuat GUI kerangka tampilan utama, tampilan draw phase, dan tampilan game finish, Membuat fungsionalitas draw phase, Membuat kelas KartuSpellMorph dan KartuSpellSwap, Membuat Test kelas Player



## Authors

<b>K03 - Menkrep</b>
| NIM       | Name                           |
| --------- | ------------------------------ |
| 13520057  | Marcellus Michael Herman K     |
| 13520066  | Putri Nurhaliza                |
| 13520084  | Adelline Kania Setiyawan       |
| 13520087  | Dimas Shidqi Parikesit         |
| 13520111  | Rizky Akbar Asmaran            |
| 13520153  | Vito Ghifari                   |

TUGAS BESAR 2 -  IF2210 Object Oriented Programming
Bandung Institute of Technology
