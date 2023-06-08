export class PatentObrazac {
  public patent: any;

  constructor(obj: any) {
    console.log(obj)
    const getFieldText = (field: any) => field?._text;

    const getFieldValue = (field: any) => {
      const fieldValue = getFieldText(field);
      return fieldValue ? fieldValue : '';
    };

    const parseAdresa = (adresa: any) => {
      return {
        mesto: getFieldValue(adresa['ns2:mesto']),
        ulica: getFieldValue(adresa['ns2:ulica']),
        broj: getFieldValue(adresa['ns2:broj']),
        drzava: getFieldValue(adresa['ns2:drzava']),
        postanskiBroj: getFieldValue(adresa['ns2:postanski_broj']),
      };
    };

    const pronalazak = obj['zahtev_za_priznanje_patenta']['pronalazak'];
    const podnosilac = obj['zahtev_za_priznanje_patenta']['podaci_o_podnosiocu'];
    const pronalazac = obj['zahtev_za_priznanje_patenta']['podaci_o_pronalazacu'];
    const punomocnik = obj['zahtev_za_priznanje_patenta']['podaci_o_punomocniku'];
    const dostavljanje = obj['zahtev_za_priznanje_patenta']['podaci_o_dostavljanju'];
    const osnovnaPrijava = obj['zahtev_za_priznanje_patenta']['osnovna_prijava'];
    const ranijePrijave = obj['zahtev_za_priznanje_patenta']['ranije_prijave'];

    this.patent = {
      statusZahteva: getFieldText(obj['zahtev_za_priznanje_patenta']['statusZahteva']),
      nazivPronalaska: {
        nazivPronalaskaRS: getFieldText(pronalazak['naziv_pronalaska_rs']),
        nazivPronalaskaENG: getFieldText(pronalazak['naziv_pronalaska_eng']),
      },
      podnosilac: {
        tipPodnosioca: podnosilac['podnosilac']['_attributes']['xsi:type'] === 'ns2:TFizicko_Lice' ? 'fizickoLice' : 'pravnoLice',
        jePronalazac: getFieldText(podnosilac['je_pronalazac']) === 'true',
        adresa: parseAdresa(podnosilac['podnosilac']['ns2:adresa']),
        ime: getFieldValue(podnosilac['podnosilac']['ns2:puno_ime']['ns2:ime']),
        prezime: getFieldValue(podnosilac['podnosilac']['ns2:puno_ime']['ns2:prezime']),
        poslovnoIme: getFieldValue(podnosilac['podnosilac']['ns2:poslovno_ime']),
        email: getFieldValue(podnosilac['podnosilac']['ns2:email']),
        brojTelefona: getFieldValue(podnosilac['podnosilac']['ns2:broj_mobilnog_telefona']),
        brojFiksnogTelefona: getFieldValue(podnosilac['podnosilac']['ns2:broj_fiksnog_telefona']),
        drzavljanstvo: {
          tipDrzavljanstva: getFieldText(podnosilac['podnosilac']['ns2:drzavljanstvo']['ns2:tip_drzavljanstva']),
          jmbg: getFieldValue(podnosilac['podnosilac']['ns2:drzavljanstvo']['ns2:jmbg']),
          brojPasosa: getFieldValue(podnosilac['podnosilac']['ns2:drzavljanstvo']['ns2:broj_pasosa']),
        },
      },
      pronalazac: {
        neZeliDaBudeNaveden: getFieldText(pronalazac['ne_zeli_da_bude_naveden']) === 'true',
        email: getFieldValue(pronalazac['pronalazac']['ns2:email']),
        brojTelefona: getFieldValue(pronalazac['pronalazac']['ns2:broj_mobilnog_telefona']),
        brojFaksa: getFieldValue(pronalazac['pronalazac']['ns2:broj_faksa']),
        ime: getFieldValue(pronalazac['pronalazac']['ns2:puno_ime']['ns2:ime']),
        prezime: getFieldValue(pronalazac['pronalazac']['ns2:puno_ime']['ns2:prezime']),
        adresaPronalazaca: parseAdresa(pronalazac['pronalazac']['ns2:adresa']),
      },
      punomocnik: {
        punomocnikZaPrijemPismena: getFieldText(punomocnik['punomocnik_za_prijem_pismena']) === 'true',
        tipPunomocnika: punomocnik['punomocnik']['_attributes']['xsi:type'] === 'ns2:TFizicko_Lice' ? 'fizickoLice' : 'pravnoLice',
        ime: getFieldText(punomocnik['punomocnik']['ns2:puno_ime']['ns2:ime']),
        prezime: getFieldText(punomocnik['punomocnik']['ns2:puno_ime']['ns2:prezime']),
        adresa: parseAdresa(punomocnik['punomocnik']['ns2:adresa']),
        email: getFieldText(punomocnik['punomocnik']['ns2:email']),
        brojTelefona: getFieldText(punomocnik['punomocnik']['ns2:broj_mobilnog_telefona']),
        brojFaksa: getFieldText(punomocnik['punomocnik']['ns2:broj_faksa']),
      },
      podacioDostavljanju: {
        nacinDostavljanja: getFieldText(dostavljanje['nacin_dostavljanja']),
        adresa: parseAdresa(dostavljanje['ns2:adresa']),
      },
      osnovnaPrijava: osnovnaPrijava ? {
        dopunskaPrijava: getFieldText(osnovnaPrijava['ns3:dopunska_prijava']) === 'true',
        brojOsnovnePrijave: getFieldValue(osnovnaPrijava['ns3:broj_osnovne_prijave']),
        datumOsnovnePrijave: getFieldValue(osnovnaPrijava['ns3:datum_osnovne_prijave']),
      } : null,
      ranijePrijave: (ranijePrijave && ranijePrijave.ranija_prijava) ? ranijePrijave.ranija_prijava.map((ranijaPrijava) => ({
        brojPrijave: getFieldValue(ranijaPrijava.broj_prijave),
        datumPodnosenja: getFieldValue(ranijaPrijava.datum_podnosenja),
        oznakaDrzave: getFieldValue(ranijaPrijava.oznaka_drzave),
      })) : [],
    };
  }
}
