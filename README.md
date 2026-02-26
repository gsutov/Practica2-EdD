# Practica2-EdD
Practica correspondiente a la clase de Estructura de Datos 2026-2 <br>
<br>
**Descripción de la práctica**<br>
# Estructura de Datos 2026-2 <br>
## Práctica 2: Arreglos <br>
**Elaboró:** Erik Quintero Villeda <br>
---
### 1. Objetivos <br>
Repasar el tema de **arreglos** y **abstracción de datos**. <br>
### 2. Instrucciones <br>
Implementa los métodos faltantes de las clases: `Participante`, `Cinta` y `AdivinadorResultados`. <br>

Dado un participante `a` y un grupo de participantes `g`, se requiere: <br>

1. Determinar a cuáles participantes del grupo `g` el participante `a` **perdería**. <br>
2. Determinar frente a cuáles participantes del grupo `g` el participante `a` **ganaría**. <br>
3. Determinar el **índice de victorias promedio** de todos los participantes del grupo `g`. <br>
4. Determinar cuáles participantes tienen la **misma cinta** que el alumno `a`. <br>
5. Determinar qué participantes del grupo presentan un índice de victoria cuya **diferencia** respecto al índice de victoria del alumno `a` sea, como máximo, de `0.10`. <br>
---
### Ejecución <br>
Se utilizará la clase `AdivinadorResultados`, la cual contiene el método `main` que leerá un archivo de texto `.txt` con el siguiente formato por línea: <br>

```
<Nombre>,M:<NúmeroMedallas>,P:<NúmeroDeParticipaciones>,C:<NombreDeLaCinta>
```

La primera línea del archivo corresponde al alumno de referencia `a`, y las líneas restantes contienen la información de los demás participantes del torneo. <br>
