# Kentung Blockchain

## 📌 Deskripsi Proyek
Kentung Blockchain adalah implementasi dasar dari blockchain menggunakan **Spring Boot**. Proyek ini memungkinkan Anda untuk **memulai blockchain**, **menambang blok baru**, dan **mengelola transaksi** dalam blockchain.

## 🚀 Fitur Utama
- **Memulai blockchain** dengan genesis block
- **Menambang blok baru** dengan Proof of Work
- **Menambahkan transaksi** ke dalam blockchain
- **Mendapatkan daftar semua blok** dalam blockchain
- **Mendapatkan dan menghapus semua transaksi** sebelum ditambang

## 🏗️ Teknologi yang Digunakan
- **Java 17**
- **Spring Boot**
- **Lombok**
- **Jakarta Validation**
- **Maven**

## 📂 Struktur Proyek
```
com.kentung.kentungBlockChain
├── controller      # Mengatur request HTTP untuk blockchain & transaksi
├── model          # Model untuk blockchain, blok, transaksi
├── service        # Business logic untuk blockchain & transaksi
├── serviceImpl    # Implementasi service
├── utils          # Utility untuk mapping
├── constant       # Konstanta route API
└── dto            # Data Transfer Objects (DTO) untuk request & response
```

## 🔧 Cara Menjalankan Aplikasi

### 1️⃣ Prasyarat
- **Java 17** terinstal di komputer Anda
- **Maven** sudah dikonfigurasi
- **IDE (IntelliJ IDEA, VS Code, Eclipse, dll.)**

### 2️⃣ Clone Repository
```bash
git clone
```

### 3️⃣ Jalankan Aplikasi
```bash
mvn spring-boot:run
```
Atau, jalankan melalui IDE Anda

## 📡 API Endpoint
### 1️⃣ **Blockchain Controller** (`/blockchain`)
| Method | Endpoint     | Deskripsi |
|--------|-------------|------------|
| POST   | `/start`    | Memulai blockchain dengan genesis block |
| POST   | `/mine`     | Menambang blok baru |
| GET    | `/`         | Mendapatkan semua blok dalam blockchain |

### 2️⃣ **Transaction Controller** (`/transaction`)
| Method | Endpoint     | Deskripsi |
|--------|-------------|------------|
| POST   | `/`         | Menambahkan transaksi baru |
| GET    | `/`         | Mendapatkan semua transaksi |
| DELETE | `/`         | Menghapus semua transaksi |

## 📬 Contoh API Request
### Menambang Blok Baru
#### **Request**
```json
POST /blockchain/mine
{
  "minerAddress": "0x123456789"
}
```
#### **Response**
```json
{
  "message": "Berhasil menambang Block Baru",
  "newBlock": {
    "index": 2,
    "previousHash": "abc123",
    "timestamp": "2024-02-03T10:00:00",
    "transactions": [],
    "hash": "xyz456",
    "nonce": 12345
  }
}
```

## 📜 Lisensi
MIT License

---

🔥 **Selamat mencoba Kentung Blockchain!** 🚀

