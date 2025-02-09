# Kentung Blockchain

## 📌 Deskripsi Proyek
Kentung Blockchain adalah implementasi dasar dari blockchain menggunakan **Spring Boot**. Proyek ini memungkinkan Anda untuk **memulai blockchain**, **menambang blok baru**,**node management** dan **mengelola transaksi** dalam blockchain.

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

## Cara Menjalankan

1. Clone repository ini:
   ```bash
   git clone https://github.com/hudzzz01/my-java-block-chain.git
   ```

2. Masuk ke direktori proyek:
   ```bash
   cd blockchain-api
   ```

3. Jalankan aplikasi menggunakan Maven:
   ```bash
   mvn spring-boot:run
   ```

4. Aplikasi akan berjalan di `http://localhost:8080`.

## Endpoint API

### 1. **Transaksi**

#### `GET /api/v1/transaction`
Mendapatkan daftar semua transaksi.

**Response:**
- 200 OK: Mengembalikan daftar transaksi.

#### `POST /api/v1/transaction`
Menambahkan transaksi baru.

**Request Body:**
```json
{
  "sender": "string",
  "reciver": "string",
  "amount": 1000
}
```

**Response:**
- 200 OK: Mengembalikan detail transaksi yang baru ditambahkan.

#### `DELETE /api/v1/transaction`
Menghapus semua transaksi.

**Response:**
- 200 OK: Mengonfirmasi bahwa transaksi telah dihapus.

---

### 2. **Node Management**

#### `POST /api/v1/node/addNode`
Menambahkan node baru ke jaringan.

**Request Body:**
```json
"node_url"
```

**Response:**
- 200 OK: Mengembalikan status penambahan node.

#### `GET /api/v1/node/readAllNode`
Mendapatkan daftar semua node yang terhubung.

**Response:**
- 200 OK: Mengembalikan daftar node yang terhubung.

#### `DELETE /api/v1/node/removeNode`
Menghapus node dari jaringan.

**Request Body:**
```json
{
  "node_id": "integer"
}
```

**Response:**
- 200 OK: Mengonfirmasi bahwa node telah dihapus.

---

### 3. **Blockchain**

#### `POST /api/v1/blockChain/start`
Memulai blockchain dengan membuat blok pertama (genesis block).

**Response:**
- 200 OK: Mengembalikan status blockchain yang telah dimulai.

#### `POST /api/v1/blockChain/mine`
Menambang blok baru.

**Request Body:**
```json
{
  "minerAddress": "string"
}
```

**Response:**
- 200 OK: Mengembalikan blok baru yang berhasil ditambang.

#### `GET /api/v1/blockChain`
Mendapatkan semua blok yang ada di blockchain.

**Response:**
- 200 OK: Mengembalikan daftar blok.

#### `GET /api/v1/blockChain/validate`
Validasi apakah blockchain valid.

**Response:**
- 200 OK: Mengembalikan status validitas blockchain.

#### `GET /api/v1/blockChain/sync`
Menyinkronkan blockchain antar node.

**Response:**
- 200 OK: Mengembalikan status sinkronisasi.

---

### 4. **Response Format**

Berikut adalah format standar dari beberapa response yang digunakan dalam API ini.

#### **TransactionResponse**
```json
{
  "id": "string",
  "sender": "string",
  "reciver": "string",
  "timeStamp": "string",
  "amount": 1000,
  "transactionHash": "string"
}
```

#### **CommondResponse**
```json
{
  "statusCode": 200,
  "message": "string",
  "data": "object"
}
```

#### **BlockResponse**
```json
{
  "previousBlockHashWithNonce": "string",
  "blockHashWithNonce": "string",
  "blockHash": "string",
  "nonce": "string",
  "transactions": "string",
  "index": "string",
  "difficuty": "string",
  "timeStampNonce": "string",
  "timeStamp": "string"
}
```

## Penggunaan

- Anda bisa mengakses API ini untuk melakukan operasi terkait blockchain seperti menambahkan transaksi, menambang blok, memvalidasi blockchain, dan banyak lagi.
- Untuk pengujian atau penggunaan langsung, Anda dapat menggunakan alat seperti Postman atau cURL untuk mengirim request ke endpoint yang relevan.

---

Jika kamu ada pertanyaan atau ingin berkontribusi, silakan buat **issue** atau **pull request**!

## License

MIT License.

---