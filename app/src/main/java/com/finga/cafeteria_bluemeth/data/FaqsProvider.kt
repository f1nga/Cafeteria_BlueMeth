package com.finga.cafeteria_bluemeth.data

import com.finga.cafeteria_bluemeth.models.Faq

class FaqsProvider {
    companion object {
        fun getFaqs(): List<Faq> {
            return listOf(
                Faq("¿Por qué debería elegir BlueMeth como mi cafetería de confianza?", "En BlueMeth ofrecemos un servicio esplendido y de primeras calidades, una vez pruebe nuestra comida quedará enamorado"),
                Faq("¿Debo registrarme?", "Usted podrá ver la lista de platos disponibles sin estar registrado, pero para hacer la comanda es necesario que usted haya iniciado sesión"),
                Faq("¿Qué hago si tengo problemas con la aplicación?", "Si la aplicación se cierra inesperadamente o tienes cualquier otro problema, comprueba en primer lugar que tienes instalada la última versión. Si el problema persiste, puedes ponerte en contacto con nosotros a través del teléfono 3636363636 o del correo holdehol@vagibe.com."),
                Faq("¿Cómo me registro?", "Entrando en el apartado de registro, simplemente necesitará un correo electrónico y una contraseña"),
                Faq("Cómo hago una comanda?", "Deberá navegar por las pantallas de los platos y podrá seleccionar los que más desee"),
                Faq("¿Cómo navego entre entornos?", "Arrastrando la pantalla con un movimiento lateral de izquierda a derecha y viceversa. Así aparecen cuatro entornos: Primer plato, segundo plato postre y pagamiento."),
                Faq("¿Puedo utilizar la aplicación si no tengo wifi ni conexión móvil a Internet?", "Usted podrá entrar en la aplicación sin sesión abierta, por lo tanto no podrá hacer comandas sin conexión internet"),
            )
        }
    }

}