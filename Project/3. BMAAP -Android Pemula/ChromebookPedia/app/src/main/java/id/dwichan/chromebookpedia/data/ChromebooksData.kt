package id.dwichan.chromebookpedia.data

import id.dwichan.chromebookpedia.R
import id.dwichan.chromebookpedia.data.entity.Chromebook

object ChromebooksData {
    private val images = intArrayOf(
        R.drawable.asus_c425,
        R.drawable.lenovo_300e,
        R.drawable.asus_c204,
        R.drawable.lenovo_14e,
        R.drawable.acer_spin_513,
        R.drawable.zyrex_m432_2,
        R.drawable.zyrex_360,
        R.drawable.advan_116,
        R.drawable.lenovo_100e,
        R.drawable.lenovo_500e
    )

    private val names = arrayOf(
        "ASUS Chromebook C425",
        "Lenovo 300e Chromebook 2nd Gen AST",
        "ASUS Chromebook C204",
        "Lenovo 14e Chromebook",
        "Acer Chromebook Spin 513",
        "Zyrex Chromebook M432-2",
        "Zyrex Chromebook 360-1",
        "Advan Chromebook 116",
        "Lenovo 100e Chromebook (2nd Gen, MTK)",
        "Lenovo 500e Chromebook 2nd Gen",
    )

    private val companies = arrayOf(
        "ASUS",
        "Lenovo",
        "ASUS",
        "Lenovo",
        "Acer",
        "zyrex",
        "zyrex",
        "Advan",
        "Lenovo",
        "Lenovo"
    )

    private val descriptions = arrayOf(
        "Sederhanakan hidup Anda dengan ASUS Chromebook C425 yang ramping dan trendi, laptop 1,3kg-ringan 14-inci yang memiliki semua kekuatan untuk membuat pekerjaan ringan dalam tugas sehari-hari Anda. Didukung oleh prosesor Intel Core i5, ASUS Chromebook C425 meningkatkan produktivitas Anda dan memungkinkan Anda lebih bersenang-senang saat bepergian - di mana pun Anda berada, apa pun yang Anda lakukan. Desain NanoEdge-nya yang ringkas dapat dengan mudah dimasukkan ke dalam tas apa pun, dan baterainya sepanjang hari menghilangkan kecemasan stopkontak untuk gaya hidup bebas kekhawatiran. Dimuat sebelumnya dengan yang terbaik dari Google, ASUS Chromebook C425 adalah gerbang Anda yang mudah digunakan ke berbagai macam aplikasi di Google Play Store untuk bekerja atau bermain.",
        "Laptop 300e Chromebook Generasi ke-2 AST 11.6\" kami memberikan kemudahan serba guna dari engsel 360-derajat dan teknologi multisentuh 10-titik. Perangkat ini dapat diubah menjadi empat mode berbeda yang memberikan keleluasaan cara untuk merencanakan, mengajar, belajar, dan terlibat sesuai kebutuhan. Tidak hanya itu, murid dapat berinteraksi dengan layar menggunakan cara mereka biasanya berinteraksi dengan banyak perangkat – lewat sentuhan",
        "ASUS Chromebook C204 dirancang dengan memikirkan kebutuhan untuk terus dapat diandalkan di lingkungan apapun. Selain dibangun untuk memenuhi standar kelas militer, kelengkapan untuk pembelajaran juga sangat menunjang guru dan siswa. Dikemas dengan fitur pintar untuk melindungi, seperti terdapat bagian yang memang diberikan bumper menyerupai karet, keyboard yang tahan tumpahan air, dan port I/O ultratough. Lama pakai baterai tanpa dicharge sesuai untuk kebutuhan sekolah dimana siswa tidak perlu mencari-cari colokan listrik untuk mengerjakan tugasnya. ASUS Chromebook C204 siap untuk apa saja!",
        "Temukan Lenovo 14e. Chromebook yang kuat dan serbaguna dengan prosesor tanpa hambatan dari AMD® seri A. Dirancang untuk penggunaan dengan Google Play dan Chrome Web Store, oleh karena itu kemungkinan yang diberikannya untuk murid, guru, dan bisnis menjadi tak terbatas. Dapatkan akses ke begitu banyak aplikasi dan layanan berbasis cloud, termasuk GSuite, alat kolaborasi pendukung produktivitas yang real-time. Untuk interaksi yang lebih baik, Lenovo 14e juga menawarkan pilihan layar sentuh dengan sudut pandang yang lebar.",
        """
            • Premium and durable with Metal Cover/1.2kg-15.5mm and Hi-Scratch Resistance Corning Gorilla Glass.
            • Optimized for Google Apps & Browser-based Simplicity.
            • High performance with Qualcomm® Snapdragon™ 7C and all day battery with up to 14 hours of usage.
            • Stunning 13” FHD IPS display and narrow bezel design (78% screen-to-body ratio).
        """.trimIndent(),
        "Produk Lokal (Berfungsi Optimal Apabila Terdapat Jaringan Internet)",
        "Produk Lokal (Berfungsi Optimal Apabila Terdapat Jaringan Internet)",
        "Produk Dalam Negeri Advan",
        "Ketika Anda membutuhkan laptop yang kuat untuk menangani kesibukan di ruang kelas, Lenovo 100e Chromebook (Generasi ke-2, MTK) tidak akan mengecewakan. Peranti 11.6\" ini mencakup akses ke Google Classroom, G Suite for Education ,dan ribuan aplikasi pembelajaran interaktif lainnya untuk murid dan guru. Terlebih lagi, bujet perangkat ini akan disukai oleh sekolah mana pun.",
        "Lenovo 500e Chromebook Generasi ke-2 dengan sempurna memadukan teknologi on-the-go prosesor hebat dan pengalaman ruang kelas premium. Mengusung engsel 360-derajat, perangkat ini dapat digunakan dalam empat cara dan juga memiliki pena digital untuk menulis dan mencatat pada layar. Perangkat 2-in-1 11.6\" ini terbukti tangguh untuk menangani kebutuhan pengguna apa pun saat di sekolah.",
    )

    private val memories = arrayOf(
        "4/8 GB",
        "4 GB",
        "4 GB",
        "8 GB",
        "4 GB",
        "4 GB",
        "4 GB",
        "4 GB",
        "4 GB",
        "8 GB",
    )

    private val storages = arrayOf(
        "32/64/128 GB eMMC",
        "32 GB eMMC",
        "16/32/64 GB eMMC, 64 GB iSSD",
        "64 GB eMMC",
        "64 GB eMMC",
        "32 GB eMMC",
        "32 GB eMMC",
        "32 GB HDD",
        "32 GB eMMC",
        "64 GB eMMC",
    )

    private val processors = arrayOf(
        """
            - Intel® Core™ m3-8100Y Processor 1.1 GHz (4M Cache, up to 3.4 GHz)
            - Intel® Core™ i5-8200Y Processor 1.3 GHz (4M Cache, up to 3.9 GHz)
            - Intel® Pentium® Gold 4415Y Processor 1.6 GHz (2M Cache, up to 1.6 GHz)
        """.trimIndent(),
        "AMD® A4 Dual-Core Processor (2.4GHz)",
        "Intel® Celeron® N4020 Processor 1.1 GHz (4M Cache, up to 2.8 GHz)",
        "AMD® A4 Dual-Core Processor (2.5 GHz)",
        "Qualcomm® Snapdragon™ 7c Compute Platform",
        "Intel® Celeron® Processor N4500",
        "Intel® Celeron® Processor N4020",
        "Intel® Core™ 2 Duo Processor @ 1.10 GHz",
        "MediaTek 8173C",
        "Intel® Celeron® Quad-Core Processor (2.4 GHz)",
    )

    private val displays = arrayOf(
        """
            - 14.0-inch,LCD,FHD (1920 x 1080) 16:9,IPS-level Panel,Anti-glare display,LED Backlit,250nits,NTSC: 45%,Screen-to-body ratio 85 %
            - Touch screen,14.0-inch,LCD,FHD (1920 x 1080) 16:9,IPS-level Panel,Glossy display,LED Backlit,250nits,NTSC: 45%,Screen-to-body ratio 85 %
        """.trimIndent(),
        "11.6\" HD (1366 x 768) 10-point multi-touch display IPS 250",
        """
            - 11.6-inch,LCD,HD (1366 x 768) 16:9,Anti-glare display,LED Backlit,200nits,NTSC: 45%,Screen-to-body ratio 67 %
            - Touch screen,11.6-inch,LCD,HD (1366 x 768) 16:9,IPS-level Panel,Anti-glare display,LED Backlit,200nits,NTSC: 50%,Screen-to-body ratio 67 %
        """.trimIndent(),
        """
            - 14" FHD TN
            - 14" FHD IPS touch (optional)
        """.trimIndent(),
        "33.8 cm (13.3\"), IPS, high-brightness LED-backlit TFT LCD, multi-touch, 16:9 aspect ratio, Wide viewing angle up to 170 degrees, Mercury free, environment friendly, Hi-Scratch Resistance Corning Gorilla Glass",
        "11.6 Inch LED, HD 1366 x 768 resolution, 16:9 aspect ratio",
        "11.6″ LED HD (TouchScreen)",
        "11 Inch LED",
        "11.6\" HD TN antiglare (1366 x 768)",
        "11.6\" HD multitouch display (1366 x 768), IPS 250",
    )

    private val graphics = arrayOf(
        "Intel® HD Graphics 615",
        "Radeon™ R4 graphics",
        "Intel® UHD Graphics 600",
        "AMD® Integrated graphics",
        "Qualcomm® Adreno™ 618",
        "Intel® UHD Graphics integrated",
        "Intel® HD Graphics integrated",
        "Intel® HD Graphics",
        "IMG PowerVR GX6250",
        "Intel® Integrated graphics",
    )

    private val colors = arrayOf(
        "Silver",
        "Black",
        "Dark Grey",
        "Aluminium",
        "Metal",
        "Hitam",
        "Abu-Abu",
        "Hitam",
        "Black",
        "Black",
    )

    val chromebooksList: ArrayList<Chromebook>
        get() {
            val list = ArrayList<Chromebook>()
            for (position in images.indices) {
                if (images[position] != 0) {
                    val chromebook = Chromebook()
                    with (chromebook) {
                        this.image = images[position]
                        this.name = names[position]
                        this.company = companies[position]
                        this.description = descriptions[position]
                        this.memory = memories[position]
                        this.storage = storages[position]
                        this.processor = processors[position]
                        this.display = displays[position]
                        this.graphic = graphics[position]
                        this.color = colors[position]
                        list.add(this)
                    }
                }
            }
            return list
        }
}