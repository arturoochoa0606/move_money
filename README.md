# move_money


MeveMoney/
 ├── app/
 │   ├── build.gradle
 │   ├── src/
 │   │   ├── main/
 │   │   │   ├── AndroidManifest.xml
 │   │   │   ├── java/com/movemoney/
 │   │   │   │   ├── MainActivity.kt
 │   │   │   │   ├── ui/
 │   │   │   │   │   ├── WalletAppUI.kt
 │   │   │   │   │   ├── CardPage.kt
 │   │   │   │   │   ├── FundsPage.kt
 │   │   │   │   │   └── TransferPage.kt
 │   │   │   │   ├── data/
 │   │   │   │   │   ├── AppDatabase.kt
 │   │   │   │   │   ├── CardEntity.kt
 │   │   │   │   │   ├── TransactionEntity.kt
 │   │   │   │   │   ├── CardDao.kt
 │   │   │   │   │   └── TransactionDao.kt
 │   │   │   │   ├── services/
 │   │   │   │   │   └── PaymentService.kt
 │   │   │   │   └── viewmodel/
 │   │   │   │       └── WalletViewModel.kt
 │   │   │   └── res/
 │   │   │       ├── values/strings.xml
 │   │   │       └── values/themes.xml
 ├── build.gradle
 └── settings.gradle



Dinero digital, depositos y transferencias de todos los bancos y todos los paises
# Meve Money

Aplicación Android en Kotlin con Jetpack Compose y Room para gestionar tarjetas, fondos internos y transferencias.  
Proyecto educativo con integración simulada de pasarelas de pago (Stripe/PayPal).

## ✨ Funcionalidades
- Agregar tarjetas de cualquier banco.
- Recargar fondos internos desde tarjetas añadidas.
- Transferir fondos internos hacia tarjetas añadidas.
- Realizar transferencias externas a otras cuentas.
- Persistencia con Room.
- UI modular con Jetpack Compose.
- Servicios de pago simulados (listos para reemplazar por Stripe/PayPal SDK).
- ViewModel para manejar estado y lógica.

## 📂 Estructura
