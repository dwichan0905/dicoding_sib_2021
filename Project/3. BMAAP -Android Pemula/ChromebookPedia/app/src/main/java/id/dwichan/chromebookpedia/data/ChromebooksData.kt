package id.dwichan.chromebookpedia.data

import id.dwichan.chromebookpedia.R
import id.dwichan.chromebookpedia.data.entity.Chromebook

object ChromebooksData {
    private val images = intArrayOf(
        R.drawable.asus_c425,
        R.drawable.asus_c204,
    )

    private val names = arrayOf(
        "ASUS Chromebook C425",
        "ASUS Chromebook C204",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    private val descriptions = arrayOf(
        "Sederhanakan hidup Anda dengan ASUS Chromebook C425 yang ramping dan trendi, laptop 1,3kg-ringan 14-inci yang memiliki semua kekuatan untuk membuat pekerjaan ringan dalam tugas sehari-hari Anda. Didukung oleh prosesor Intel Core i5, ASUS Chromebook C425 meningkatkan produktivitas Anda dan memungkinkan Anda lebih bersenang-senang saat bepergian - di mana pun Anda berada, apa pun yang Anda lakukan. Desain NanoEdge-nya yang ringkas dapat dengan mudah dimasukkan ke dalam tas apa pun, dan baterainya sepanjang hari menghilangkan kecemasan stopkontak untuk gaya hidup bebas kekhawatiran. Dimuat sebelumnya dengan yang terbaik dari Google, ASUS Chromebook C425 adalah gerbang Anda yang mudah digunakan ke berbagai macam aplikasi di Google Play Store untuk bekerja atau bermain.",
        "ASUS Chromebook C204 dirancang dengan memikirkan kebutuhan untuk terus dapat diandalkan di lingkungan apapun. Selain dibangun untuk memenuhi standar kelas militer, kelengkapan untuk pembelajaran juga sangat menunjang guru dan siswa. Dikemas dengan fitur pintar untuk melindungi, seperti terdapat bagian yang memang diberikan bumper menyerupai karet, keyboard yang tahan tumpahan air, dan port I/O ultratough. Lama pakai baterai tanpa dicharge sesuai untuk kebutuhan sekolah dimana siswa tidak perlu mencari-cari colokan listrik untuk mengerjakan tugasnya. ASUS Chromebook C204 siap untuk apa saja!",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    private val memories = arrayOf(
        "4/8 GB",
        "4 GB",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    private val storages = arrayOf(
        "32/64/128 GB eMMC",
        "16/32/64 GB eMMC, 64 GB iSSD",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    private val processors = arrayOf(
        """
            - Intel® Core™ m3-8100Y Processor 1.1 GHz (4M Cache, up to 3.4 GHz)
            - Intel® Core™ i5-8200Y Processor 1.3 GHz (4M Cache, up to 3.9 GHz)
            - Intel® Pentium® Gold 4415Y Processor 1.6 GHz (2M Cache, up to 1.6 GHz)
        """.trimIndent(),
        "Intel® Celeron® N4020 Processor 1.1 GHz (4M Cache, up to 2.8 GHz)",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    private val displays = arrayOf(
        """
            - 14.0-inch,LCD,FHD (1920 x 1080) 16:9,IPS-level Panel,Anti-glare display,LED Backlit,250nits,NTSC: 45%,Screen-to-body ratio 85 %
            - Touch screen,14.0-inch,LCD,FHD (1920 x 1080) 16:9,IPS-level Panel,Glossy display,LED Backlit,250nits,NTSC: 45%,Screen-to-body ratio 85 %
        """.trimIndent(),
        """
            - 11.6-inch,LCD,HD (1366 x 768) 16:9,Anti-glare display,LED Backlit,200nits,NTSC: 45%,Screen-to-body ratio 67 %
            - Touch screen,11.6-inch,LCD,HD (1366 x 768) 16:9,IPS-level Panel,Anti-glare display,LED Backlit,200nits,NTSC: 50%,Screen-to-body ratio 67 %
        """.trimIndent(),
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    private val graphics = arrayOf(
        "Intel® HD Graphics 615",
        "Intel® UHD Graphics 600",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    private val colors = arrayOf(
        "Silver",
        "Dark Grey",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )

    val chromebooksList: ArrayList<Chromebook>
        get() {
            val list = ArrayList<Chromebook>()
            for (position in images.indices) {
                val chromebook = Chromebook()
                with (chromebook) {
                    this.image = images[position]
                    this.name = names[position]
                    this.description = descriptions[position]
                    this.memory = memories[position]
                    this.storage = storages[position]
                    this.processor = processors[position]
                    this.display = displays[position]
                    this.graphic = graphics[position]
                    this.color = colors[position]
                    list.add(chromebook)
                }
            }
            return list
        }
}