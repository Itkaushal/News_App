# 📰 News MVVM App

🚀 **News MVVM App** is a modern Android application built using **Kotlin**, 
following the **MVVM (Model-View-ViewModel) architecture**. It integrates **Retrofit**,
**Coroutines**, **Paging 3**, **Room Database**, **Glide**, and **Navigation Component** 
for a seamless news browsing experience.

## 📌 **Features**
✅ Fetch latest news from REST API using **Retrofit** 📡  
✅ News Api use of newsapi.org 🚀🌎
✅ Implement **MVVM Architecture** for clean & maintainable code 🏗️  
✅ Use **Coroutines & Flow** for asynchronous programming ⚡  
✅ Store articles offline using **Room Database** 🗃️  
✅ Load images efficiently with **Glide** 🖼️  
✅ Smooth pagination using **Paging 3** 📑  
✅ Navigate between fragments using **Navigation Component** 🧭  
✅ Search news articles by keyword 🔎  
✅ Save favorite articles locally ❤️  

---

## 📂 **Tech Stack & Libraries Used**

### 🏗️ **MVVM Architecture**
- **ViewModel**: Separates UI logic from business logic 📊
- **LiveData**: Observes and updates UI in real-time 🔄
- **Repository Pattern**: Manages API and database operations 🏢

### 🔗 **Networking - Retrofit & Coroutines**
- **Retrofit**: Handles API calls smoothly 🌐
- **Moshi/Gson**: Parses JSON responses 📜
- **Coroutines & Flow**: Manages background tasks asynchronously ⚡

### 🗃️ **Local Storage - Room Database**
- **Entity, DAO, Database**: Stores news articles offline 📥
- **ViewModel + LiveData**: Fetches & observes database changes 📡

### 📑 **Paging 3 - Efficient Pagination**
- **RecyclerView Adapter**: Displays paginated data 📜
- **PagingSource**: Handles API pagination 🚀
- **RemoteMediator**: Loads both remote & local data 📦

### 🖼️ **Glide - Image Loading**
- **Efficient image caching & loading** 🎨
- **Placeholder & error handling** 🚨

### 🧭 **Navigation Component**
- **SafeArgs**: Pass data between fragments safely 🚦
- **Single Activity Architecture** 🏛️

### 🎨 **ViewBinding**
- **Type-safe access to views** 🎯
- **Improves performance & avoids `findViewById()`** 🚀

---

## 📥 **Installation & Setup**

### 🔗 **Clone the Repository**
```sh
git clone https://github.com/Itkaushal/News_App.git
```

### 🚀 **Open in Android Studio**
1. Open **Android Studio**
2. Click on **File → Open**
3. Select the **cloned folder**
4. Wait for **Gradle Sync** to complete
5. Run the **app on an emulator or real device** 📱

### 🏗️ **Dependencies - Add to `build.gradle (Module: app)`**
```gradle
dependencies {
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.1'
    implementation 'androidx.room:room-runtime:2.5.0'
    kapt 'androidx.room:room-compiler:2.5.0'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.github.bumptech.glide:glide:4.14.2'
    implementation 'androidx.paging:paging-runtime-ktx:3.1.1'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.7.5'
    implementation 'androidx.navigation:navigation-ui-ktx:2.7.5'
}
```

## 🎯 **How to Use the App**
1. **Browse the latest news** on the home screen 📰
2. **Search for articles** using the search bar 🔎
3. **Save favorite articles** for later reading ❤️
4. **View full news articles** inside WebView 🌍
5. **Navigate seamlessly** between different sections 📲

## 🛠️ **Contributing**
💡 Found a bug? Want to add a feature? Feel free to **fork** & submit a **pull request**!

1. Fork the project 🍴
2. Create your **feature branch** (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Open a **Pull Request** 🔥

## 📩 **Contact & Support**
📧 Email: [kaushalprajapati9953@gmail.com](mailto:kaushalprajapati9953@gmail.com)  
💬 LinkedIn: [Kaushlendra Prajapati](https://linkedin.com/in/kaushlendra-prajapati)  

## ⭐ **Show Your Support!**
If you liked this project, don’t forget to **give it a star ⭐** on GitHub!

📌 **GitHub Repo:** [News MVVM App](https://github.com/Itkaushal/News_App/)

🚀 Happy Coding! 🎉

![1](https://github.com/user-attachments/assets/d433921a-db18-4181-ac13-4505dd9d81dc)
![2](https://github.com/user-attachments/assets/3d6b5ed1-2d04-4abc-b935-6812268d8c0d)
![3](https://github.com/user-attachments/assets/9550fb41-338e-40c0-b08f-c2f7b3187091)




