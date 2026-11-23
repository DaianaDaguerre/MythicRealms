# Mythic Realms — Alpha 0.2.1 (Fabric 1.21.1)

Versión corregida del mod. Incluye el unicornio y los seis materiales (los cinco originales más pelo de unicornio).

## Correcciones
- Sustituido el método privado `setHorseVariant` por `setVariant`, compatible con Yarn 1.21.1.
- Sustituido `SpawnLocation.ON_GROUND` por `SpawnLocationTypes.ON_GROUND`.
- Añadido el cuerno a la pieza `head_parts` del modelo equino.
- GitHub Actions descarga solo `mythicrealms-0.2.1.jar`, no el JAR `sources`.

## Contenido
- `mythicrealms:unicornio` con textura y modelo provisionales, movimiento y montura de caballo.
- Generación natural en bosque florido y pradera de montaña.
- Domesticación con manzana dorada y guardado usando el sistema de los caballos.
- Reproducción con zanahoria dorada.
- `mythicrealms:pelo_unicornio`, obtenido ocasionalmente de adultos domesticados.
- Cinco materiales de la versión 0.1 y huevo de aparición.

## Cómo subir a GitHub desde el navegador
1. Descomprimí el ZIP.
2. En el repositorio `DaianaDaguerre/MythicRealms`, usá **Add file → Upload files**.
3. Arrastrá los archivos y las carpetas desde esta carpeta, **conservando todas las rutas**, especialmente `src/main/...` y `.github/workflows/build.yml`.
4. Confirmá **Commit changes** en la rama `main`. No subas solo los archivos `.java` sueltos.
5. En **Actions**, esperá la ejecución del commit más reciente y descargá el artifact `MythicRealms-Fabric-1.21.1-0.2.1`.

## Instalar
Colocá **solamente** `mythicrealms-0.2.1.jar` en `%appdata%\\.minecraft\\mods`, junto a Fabric API compatible con Minecraft 1.21.1. Quitá JAR antiguos de Mythic Realms y no instales un `*-sources.jar`.

## Probar
En Minecraft 1.21.1, en un mundo con trucos activados:

```mcfunction
/summon mythicrealms:unicornio ~ ~ ~
/give @s mythicrealms:huevo_unicornio
/give @s minecraft:golden_apple 3
/give @s minecraft:golden_carrot 3
/give @s minecraft:saddle
/give @s mythicrealms:pelo_unicornio
```

**Nota:** el código fuente está revisado, pero la compilación con Gradle y el renderizado dentro del juego requieren validación en GitHub Actions/Minecraft.
