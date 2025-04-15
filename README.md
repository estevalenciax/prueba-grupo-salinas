# Prueba Técnica - Formulario + Pokedex

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)

Aplicación Android que muestra un formulario con listado de Pokemons y detalles.

## Características

- Validación de datos en tiempo real
- Consumo de API de terceros
- Manejo de estados de carga
- Almacenamiento de datos en caché

## Tecnologías Utilizadas

| Componente       | Tecnología           |
|------------------|----------------------|
| UI               | Jetpack Compose      |
| Arquitectura     | MVVM + Clean Arquitecture |
| Almacenamiento   | Room                 |
| Cliente HTTP     | Retrofit             |
| Inyección de dependencias | Dagger Hilt          |
| Navegación     | Navigation Components             |
| Asíncronia     | Corrutinas             |

## Comentarios sobre diseño
El diseño de la pantalla de login se pensó tomando en cuenta la temática del resto de la aplicación. Se usaron elementos característicos como el logo de Pokemon y una imagen aluciente 
a los formularios de entrenadores en el juego real ya que los datos coinciden. Las formas redondeadas se eligieron para tener un diseño amigable. 
Esta pantalla se mejoraria agregando colores característicos de la saga.

El diseño del listado de pokemons y detalles esta basado en el diseño de un GIF encontrado en internet. Lo que llamó la atención es diseño colorido y amigable que se buscaba desde un inicio.
Estas pantallas se mejorarían agregando datos como el número de Pokemon en la Pokedex original, imagen del Pokemon y los tipos, esos datos no los proporciona el api, solamente en el detalle. 
También se agregarían colores caracteristicos y el soporte correcto a tema oscuro en dispositivos.
