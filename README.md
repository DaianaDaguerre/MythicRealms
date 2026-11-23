# Mythic Realms — Alpha 0.2.4 · Corrección del unicornio

Minecraft Java 1.21.1 · Fabric · Java 21.

## Qué estaba mal

1. En GitHub las ocho texturas quedaron sueltas **en la raíz**. El motor solo lee las texturas bajo `src/main/resources/assets/mythicrealms/textures/entity/`.
2. El código del cuerno utilizaba coordenada Z -9, correspondiente al hocico. Se cambió a Z -2.5, cerca de la frente, unido a `head_parts`.
3. El `UnicornEntity.java` / `UnicornRenderer.java` que estaban en `src/main` eran versiones viejas, aunque aparecían archivos nuevos con los mismos nombres en la raíz. **Solo se compila lo que está dentro de `src/main/java/`**.

## Archivos obligatorios
- `src/main/java/com/mythicrealms/entity/UnicornEntity.java`
- `src/main/java/com/mythicrealms/client/UnicornRenderer.java`
- `src/main/java/com/mythicrealms/client/MythicRealmsClient.java`
- `src/main/resources/assets/mythicrealms/textures/entity/unicornio_*.png` (**ocho PNG, 64x64**)
- `gradle.properties` (`mod_version=0.2.4`)
- `.github/workflows/build.yml`

## Cómo subir sin que se desordenen las carpetas

**Opción fiable desde el navegador de GitHub:**

1. Descomprimir este ZIP. NO subir el ZIP directamente.
2. En GitHub, ir a `src/main/java/com/mythicrealms/entity/` y editar o reemplazar allí `UnicornEntity.java`. NO editar el duplicado de la raíz.
3. En `src/main/java/com/mythicrealms/client/` editar o reemplazar allí `UnicornRenderer.java` y `MythicRealmsClient.java`.
4. En `src/main/java/com/mythicrealms/` editar o reemplazar `MythicRealms.java`.
5. Ir a `src/main/resources/assets/mythicrealms/textures/entity/` y usar **Add file → Upload files** para subir **solo los 8 PNG** de la carpeta `textures/entity/` del ZIP.
6. En la raíz, reemplazar `gradle.properties`. En `.github/workflows/`, reemplazar `build.yml`.
7. En GitHub Actions comprobar ejecución verde, descargar el artifact con `mythicrealms-0.2.4.jar` e instalar solo ese JAR.
8. Borrar de `mods` todos los JAR antiguos de Mythic Realms. No instalar `-sources.jar`.

> También podés usar un editor que preserve rutas (GitHub web editor en `github.dev`) para subir todas las carpetas de una vez; la carga de archivos sueltos en la raíz **no funciona**.

## Pruebas en un mundo con trucos

Invocar ocho unicornios por separado con datos persistentes:

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

Colores: 0 blanco (30 %), 1 celeste (25 %), 2 rosado (25 %), 3 verde (7 %), 4 azul (6 %), 5 amarillo (5 %), 6 violeta (1,5 %) y 7 rojo (0,5 %).

Inspeccionar NBT de un unicornio cercano:

```mcfunction
/data get entity @e[type=mythicrealms:unicornio,sort=nearest,limit=1] MythicCoat
```

**Importante:** la geometría y texturas se revisaron estáticamente. No se verificaron visualmente en Minecraft, y pueden requerir ajustes tras una prueba dentro del juego.
