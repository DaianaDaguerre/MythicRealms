# Mythic Realms — Unicornio de fantasía (Alpha 0.2.2)

Proyecto completo para **Minecraft Java 1.21.1 / Fabric / Java 21**.

## Qué se añadió
- Ocho variantes de pelaje en texturas originales de 64×64: blanco (30%), celeste (25%), rosado (25%), verde (7%), azul (6%), amarillo (5%), violeta (1,5%) y rojo (0,5%).
- El color se asigna automáticamente al aparecer y se conserva al guardar el mundo.
- Reproducción: 90% de probabilidad de heredar el color de uno de los progenitores (50/50), y 10% de color aleatorio sujeto a las rarezas.
- Modelo del caballo de Minecraft para reutilizar animaciones; cuerno de tres segmentos unido a la cabeza.
- Manzana dorada para domesticar, zanahoria dorada para criar; montura de caballo con silla.
- Conserva los seis materiales y el huevo de unicornio.

**Importante:** los archivos PNG son texturas UV que se aplican al modelo, no renders. La hoja de concepto es una referencia estética, no un archivo 3D. Las texturas generadas son aproximaciones originales a esa estética y pueden ajustarse después de una prueba visual dentro de Minecraft. El cuerno es geométrico/escalonado en lugar de espiral redonda para conservar la apariencia cúbica.

## Cómo subirlo a GitHub
1. Descomprimí este ZIP y arrastrá **todas las carpetas** (`src`, `.github`, etc.) y archivos a la raíz de tu repositorio en GitHub. Es necesario conservar la estructura anidada.
2. Confirmá el commit en la rama `main`.
3. En **Actions → Compilar Mythic Realms**, revisá el resultado. Descargá el artefacto de la versión `0.2.2` y extraé únicamente el archivo `mythicrealms-0.2.2.jar`.
4. En `%appdata%\.minecraft\mods`, reemplazá el JAR anterior por el nuevo, junto con Fabric API compatible.
5. No coloques archivos `*-sources.jar` en `mods`.

## Pruebas
```
/summon mythicrealms:unicornio ~ ~ ~
/give @s mythicrealms:huevo_unicornio 16
/give @s minecraft:golden_apple 5
/give @s minecraft:golden_carrot 5
```
En un mundo nuevo podés buscar en bosques floridos y praderas de montaña.

## Verificar los colores
El color se elige al aparecer de acuerdo con los pesos establecidos. El cuerno y los pelajes requieren prueba visual dentro de Minecraft para confirmar la ubicación exacta de sus UV. Para comprobar variantes exóticas conviene invocar una cantidad considerable de unicornios.

## Estado de validación
Se verificó la estructura del ZIP, las rutas de recursos, los porcentajes y las referencias entre entidades y texturas. **No se ejecutó una compilación completa con Gradle ni una prueba dentro del juego en este entorno.** Si GitHub Actions marca error, compartir el registro para corregirlo antes de instalar.
