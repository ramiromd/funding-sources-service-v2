# API REST: Herencia y polimorfismo

## Objeto
El objeto de este proyecto es demostrar cómo gestionar recursos, de una misma familia, en una API REST.

## Caso de uso

### Funding source service

Se desea desarrollar un servicio que, administre distintos medios de financiación (**funding sources**); para usuarios
de aplicaciones de terceros.

En un principio, se busca modelar los siguientes medios: *tarjetas de crédito* (**credit_cards**) y *cuentas bancarias*
(**bank_accounts**). 

De una **tarjeta de crédito**, se deben conocer los siguientes datos; un identificador univoco del recurso, el identificador
del usuario propietario del recurso, un nombre descriptivo del recurso,  el nombre que figura en la tarjeta, 
su número de identificación bancaria (**BIN**), su franquicia, sus últimos 4 dígitos, su fecha de caducidad, la fecha de creación del 
recurso y la fecha de baja del recurso.

Mientras que, de una **cuenta bancaria** se deben conocer los siguientes datos; un identificador univoco del recurso, el 
identificador del usuario propietario del recurso, un nombre descriptivo del recurso, número de cuenta bancaria, nombre
del titular, nombre de la entidad bancaria, la fecha de creación del recurso y la fecha de baja del recurso.

No obstante, se espera que la solución pueda ser extensible a otros medios; como pueden ser:
*tarjetas de débito* (**debit_cards**), *tarjetas prepagas* (**prepaid_cards**), *cuentas digitales* (**digital_accounts**),
*cheques* (**checks**), *efectivo* (**cash**).

El servicio deberá facilitar las siguientes operaciones, a los usuarios finales: *crear un nuevo medio de pago*,
*deshabilitar un medio de pago*, *listar los medios de pagos* y *obtener el detalle de un medio de pago*.

### :-1: Solución #1

Una primera solución puede ser, modelar cada medio de financiación como un recurso aislado del resto. Cada uno con su
propio modelo, contrato y endpoint. Por ejemplo:

- /credit_cards
- /bank_accounts

Esto tiene algunas desventajas:

- Existe un endpoint por cada recurso, generando confusión y dificultando la implementación de los clientes.
- Al pensar en los modelos de forma aislada entre si, los desarrolladores podrían generar código duplicado al implementar
nuevos medios de financiación.
- Si se quisieran listar todos los medios de financiación, se debería crear un nuevo endpoint.

### :+1: Solución #2

Podemos ver a los medios de financiación, como una jerarquía; en lugar de entidades aisladas. Donde atributos en común
estén en la generalización, y los atributos propios de cada medio; en su especialización. De esta forma, podemos tener 
un único endpoint. Presumiblemente `/funding_sources`.

Esto supone las siguientes ventajas.

- El contrato del servicio es más claro y fácil de implementar. Solo se dispone de un único endpoint y, a lo sumo, se
definen nuevos objetos de especialización.
- El código del servicio es más coherente y reduce la posibilidad de que los desarrolladores ejerzan malas practicas.
- Se pueden listar todos los medios de financiación, exponiendo solo los atributos de la generalización. Luego, cuando
se solicite el detalle de un medio; el contrato será más específico.