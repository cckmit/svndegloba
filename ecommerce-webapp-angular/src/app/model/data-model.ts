export class Food {
  id = 0;
  name = '';
  description = '';
  amount = 0;
  unit = '';
  calories = 0;
  macros: Macros;
}

export class Macros {
  protein = 0;
  fat = 0;
  hc = 0;
}
export class Persona {
  id: number;
  name: string;
  email: string;
  birthday: Date;
  height: number;
  weight: number;
  gender: number;
  country: number;
  postalCode: string;
  units: Units;
  goals: Goals;

}

export class Units {
  weight = '';
  height = '';
  distance = '';
  Energy = '';
  water = '';
}
export class Goals {
  initialWeight = 0;
  currentWeight = 0;
  desireWeight = 0;
  weeklyGoal = 0;
  activityLevel = 0;
  stepsAtDay = 0;
}

export class DietDays {

  date: Date = new Date();
  goalDay: number;
  totalCalories: number;
  exercise: number;
  steps: number;
  weight: number;
  meals: Meal[] = [];
}

export class Meal {
  name = '';
  foods: Food[] = [];
}
export class UserData {
  constructor(
    public label: string,
    public value: Persona,
    public nomvar: string
  ) { }
}
export const UNITS = ['grs', 'kg', 'unit', 'l', 'ml'];
export const COUNTRIES = ['Afganistán', 'Akrotiri', 'Albania', 'Alemania', 'Andorra', 'Angola',
  'Anguila', 'Antártida', 'Antigua y Barbuda', 'Antillas Neerlandesas', 'Arabia Saudí',
  'Arctic Ocean', 'Argelia', 'Argentina', 'Armenia', 'Aruba', 'Ashmore andCartier Islands',
  'Atlantic Ocean', 'Australia', 'Austria', 'Azerbaiyán', 'Bahamas', 'Bahráin', 'Bangladesh',
  'Barbados', 'Bélgica', 'Belice', 'Benín', 'Bermudas', 'Bielorrusia', 'Birmania Myanmar',
  'Bolivia', 'Bosnia y Hercegovina', 'Botsuana', 'Brasil', 'Brunéi', 'Bulgaria', 'Burkina Faso',
  'Burundi', 'Bután', 'Cabo Verde', 'Camboya', 'Camerún', 'Canadá', 'Chad', 'Chile', 'China',
  'Chipre', 'Clipperton Island', 'Colombia', 'Comoras', 'Congo', 'Coral Sea Islands', 'Corea del Norte',
  'Corea del Sur', 'Costa de Marfil', 'Costa Rica', 'Croacia', 'Cuba', 'Dhekelia', 'Dinamarca',
  'Dominica', 'Ecuador', 'Egipto', 'El Salvador', 'El Vaticano', 'Emiratos Árabes Unidos',
  'Eritrea', 'Eslovaquia', 'Eslovenia', 'España', 'Estados Unidos', 'Estonia', 'Etiopía',
  'Filipinas', 'Finlandia', 'Fiyi', 'Francia', 'Gabón', 'Gambia', 'Gaza Strip', 'Georgia',
  'Ghana', 'Gibraltar', 'Granada', 'Grecia', 'Groenlandia', 'Guam', 'Guatemala', 'Guernsey',
  'Guinea', 'Guinea Ecuatorial', 'Guinea-Bissau', 'Guyana', 'Haití', 'Honduras', 'Hong Kong',
  'Hungría', 'India', 'Indian Ocean', 'Indonesia', 'Irán', 'Iraq', 'Irlanda', 'Isla Bouvet',
  'Isla Christmas', 'Isla Norfolk', 'Islandia', 'Islas Caimán', 'Islas Cocos', 'Islas Cook',
  'Islas Feroe', 'Islas Georgia del Sur y Sandwich del Sur', 'Islas Heard y McDonald',
  'Islas Malvinas', 'Islas Marianas del Norte', 'IslasMarshall', 'Islas Pitcairn',
  'Islas Salomón', 'Islas Turcas y Caicos', 'Islas Vírgenes Americanas', 'Islas Vírgenes Británicas',
  'Israel', 'Italia', 'Jamaica', 'Jan Mayen', 'Japón', 'Jersey', 'Jordania', 'Kazajistán', 'Kenia',
  'Kirguizistán', 'Kiribati', 'Kuwait', 'Laos', 'Lesoto', 'Letonia', 'Líbano', 'Liberia',
  'Libia', 'Liechtenstein', 'Lituania', 'Luxemburgo', 'Macao', 'Macedonia', 'Madagascar',
  'Malasia', 'Malaui', 'Maldivas', 'Malí', 'Malta', 'Man, Isle of', 'Marruecos', 'Mauricio',
  'Mauritania', 'Mayotte', 'México', 'Micronesia', 'Moldavia', 'Mónaco', 'Mongolia',
  'Montserrat', 'Mozambique', 'Namibia', 'Nauru', 'Navassa Island', 'Nepal', 'Nicaragua',
  'Níger', 'Nigeria', 'Niue', 'Noruega', 'Nueva Caledonia', 'Nueva Zelanda', 'Omán',
  'Pacific Ocean', 'Países Bajos', 'Pakistán', 'Palaos', 'Panamá', 'Papúa-Nueva Guinea',
  'Paracel Islands', 'Paraguay', 'Perú', 'Polinesia Francesa', 'Polonia', 'Portugal',
  'Puerto Rico', 'Qatar', 'Reino Unido', 'República Centroafricana', 'República Checa',
  'República Democrática del Congo', 'República Dominicana', 'Ruanda', 'Rumania', 'Rusia',
  'Sáhara Occidental', 'Samoa', 'Samoa Americana', 'San Cristóbal y Nieves', 'San Marino',
  'San Pedro y Miquelón', 'San Vicente y las Granadinas', 'Santa Helena', 'Santa Lucía',
  'Santo Tomé y Príncipe', 'Senegal', 'Seychelles', 'Sierra Leona', 'Singapur', 'Siria',
  'Somalia', 'Southern Ocean', 'Spratly Islands', 'Sri Lanka', 'Suazilandia', 'Sudáfrica',
  'Sudán', 'Suecia', 'Suiza', 'Surinam', 'Svalbard y Jan Mayen', 'Tailandia', 'Taiwán',
  'Tanzania', 'Tayikistán', 'TerritorioBritánicodel Océano Indico', 'Territorios Australes Franceses',
  'Timor Oriental', 'Togo', 'Tokelau', 'Tonga', 'Trinidad y Tobago', 'Túnez', 'Turkmenistán',
  'Turquía', 'Tuvalu', 'Ucrania', 'Uganda', 'Unión Europea', 'Uruguay', 'Uzbekistán', 'Vanuatu', 'Venezuela', 'Vietnam',
  'Wake Island', 'Wallis y Futuna', 'West Bank', 'World', 'Yemen', 'Yibuti', 'Zambia', 'Zimbabue'];
