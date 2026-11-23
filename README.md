# Mythic Realms — Alpha 0.2.3 · Unicornio de 8 pelajes

Minecraft Java **1.21.1**, **Fabric**, JDK 21.

## Variantes y rarezas

| Pelaje | Probabilidad | NBT `MythicCoat` |
| --- | ---: | ---: |
| Blanco | 30 % | 0 |
| Celeste | 25 % | 1 |
| Rosado | 25 % | 2 |
| Verde | 7 % | 3 |
| Azul | 6 % | 4 |
| Amarillo | 5 % | 5 |
| Violeta | 1,5 % | 6 |
| Rojo | 0,5 % | 7 |

Cada unicornio guarda y sincroniza su pelaje. Las crías heredan el de uno de los padres en el 90 % de los casos (si ambos son unicornios) o reciben una variante aleatoria en el otro 10 %. La domesticación usa manzana dorada, la reproducción zanahoria dorada y el modelo reutiliza animaciones de caballo.

## Subir a GitHub

**ATENCION:** este ZIP tiene la estructura correcta. Descomprimir y subir las carpetas completas `src/`, `.github/` y los archivos de la raíz con la misma jerarquía. **No subir los archivos `.java` sueltos a la raíz**. GitHub debe mostrar `src/main/java/com/mythicrealms/entity/UnicornEntity.java` y `src/main/java/com/mythicrealms/client/UnicornRenderer.java`. Si GitHub no reemplaza los archivos existentes, abrir cada uno y pegar el código actualizado manualmente.

El workflow está en `.github/workflows/build.yml`; reemplazar el anterior. Revisar **Actions** y descargar el artifact de la ejecución nueva. El único JAR válido para jugar es `mythicrealms-0.2.3.jar`. Eliminar `0.2.1` y `0.2.2` de la carpeta `mods`. Fabric API es necesario.

## Probar con variantes fijas (trucos activados)

```mcfunction
/summon mythicrealms:unicornio ~ ~ ~ {MythicCoat:0}
/summon mythicrealms:unicornio ~ ~ ~ {MythicCoat:1}
/summon mythicrealms:unicornio ~ ~ ~ {MythicCoat:2}
/summon mythicrealms:unicornio ~ ~ ~ {MythicCoat:3}
/summon mythicrealms:unicornio ~ ~ ~ {MythicCoat:4}
/summon mythicrealms:unicornio ~ ~ ~ {MythicCoat:5}
/summon mythicrealms:unicornio ~ ~ ~ {MythicCoat:6}
/summon mythicrealms:unicornio ~ ~ ~ {MythicCoat:7}
```

Sin NBT, `/summon mythicrealms:unicornio ~ ~ ~` asigna pelaje al azar según las rarezas.

**Validación:** se verifican contenido, referencias y rutas del proyecto, pero no se ejecutó Gradle ni Minecraft desde este entorno. Si Actions falla, enviar las líneas `error:` del paso Compilar.
