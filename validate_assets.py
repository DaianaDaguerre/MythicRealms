from pathlib import Path
import struct

base = Path('src/main/resources/assets/mythicrealms/textures/entity')
names = ('blanco','celeste','rosado','verde','azul','amarillo','violeta','rojo')
for name in names:
    p = base / f'unicornio_{name}.png'
    assert p.is_file(), f'Falta textura en su carpeta: {p}'
    b = p.read_bytes()
    assert b[:8] == b'\x89PNG\r\n\x1a\n' and struct.unpack('>II', b[16:24]) == (64, 64), f'PNG no valido: {p}'
entity = Path('src/main/java/com/mythicrealms/entity/UnicornEntity.java').read_text()
renderer = Path('src/main/java/com/mythicrealms/client/UnicornRenderer.java').read_text()
client = Path('src/main/java/com/mythicrealms/client/MythicRealmsClient.java').read_text()
assert 'WEIGHTS_PER_1000 = {300, 250, 250, 70, 60, 50, 15, 5}' in entity
assert 'return TEXTURES[' in renderer and 'unicornio_' in renderer
assert 'cuboid(-1.0f, -15.0f, -2.5f' in client
assert 'mod_version=0.2.4' in Path('gradle.properties').read_text()
print('OK: 8 pelajes 64x64, codigo de variantes y cuerno en frente de la cabeza')

