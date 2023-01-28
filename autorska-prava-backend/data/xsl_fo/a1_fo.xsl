<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:a1="http://www.xml.tim14.rs/autorska_prava"
                xmlns:ks="http://www.xml.tim14.rs/korisnici" version="2.0"
>
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="a1-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="a1-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:table font-family="Calibri, sans-serif" border="1px solid black" border-collapse="collapse">
                        <fo:table-column column-width="50%"/>
                        <fo:table-column column-width="50%"/>
                        <fo:table-body>

                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="1">
                                    <fo:block font-size="12" font-weight="bold">
                                        ЗАВОД ЗА ИНТЕЛЕКТУАЛНУ СВОЈИНУ
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell number-columns-spanned="1">
                                    <fo:block font-size="12" font-weight="bold">
                                        ОБРАЗАЦ А-1
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="1">
                                    <fo:block font-size="12">
                                        Београд, Кнегиње Љубице 5
                                    </fo:block>
                                </fo:table-cell>

                            </fo:table-row>
                            <fo:table-row border-bottom="1px solid black">
                                <fo:table-cell number-columns-spanned="2" padding="30px">
                                    <fo:block font-size="12" font-weight="bold">
                                        ЗАХТЕВ ЗА УНОШЕЊЕ У ЕВИДЕНЦИЈУ И ДЕПОНОВАЊЕ АУТОРСКИХ ДЕЛА
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Podnosilac -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        1) Подносилац - име, презиме, адреса и држављанство аутора или другог носиоца ауторског права ако је подносилац физичко лице, односно пословно име и седиште носиоца ауторског права ако је подносилац правно лице:<br/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding-left="20px">
                                    <xsl:choose>
                                        <xsl:when test="//a1:podnosilac//ks:puno_ime">
                                            <fo:block> Име <xsl:value-of select="//a1:podnosilac//ks:puno_ime//ks:ime"/> и
                                            презиме: <xsl:value-of select="//a1:podnosilac//ks:puno_ime//ks:prezime"/>
                                            Тип држаљанства: <xsl:value-of select="//a1:podnosilac//ks:drzavljanstvo//ks:tip_drzavljanstva"/>
                                            Јмбг: <xsl:value-of select="//a1:podnosilac//ks:drzavljanstvo//ks:jmbg"/>
                                            Број пасоша: <xsl:value-of select="//a1:podnosilac//ks:drzavljanstvo//ks:broj_pasosa"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block>Пословно име: <xsl:value-of select="//a1:podnosilac//ks:poslovno_ime"/></fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                    <fo:block> Адреса: <xsl:value-of select="//a1:podnosilac//ks:adresa//ks:ulica"/>
                                        <xsl:value-of select="//a1:podnosilac//ks:adresa//ks:broj"/>,
                                        <xsl:value-of select="//a1:podnosilac//ks:adresa//ks:mesto"/>
                                        <xsl:value-of select="//a1:podnosilac//ks:adresa//ks:postanski_broj"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row >
                                <fo:table-cell number-columns-spanned="1" padding="25px 0px 0px 8px">
                                    <fo:block border="1px solid black" font-size="11" padding="2px">
                                        телефон: <xsl:value-of select="//a1:podnosilac//ks:broj_mobilnog_telefona"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell number-columns-spanned="1" padding="25px 2px 0px 5px">
                                    <fo:block border="1px solid black" font-size="11" padding="2px">
                                        е-mail: <xsl:value-of select="//a1:podnosilac//ks:email"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Pseudonim -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        2) Псеудоним или знак аутора, (ако га има):
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2">
                                    <fo:block margin="10px 0px 10px 20px">
                                        <xsl:if test="a1:zahtev_za_autorska_prava/a1:autorsko_delo/a1:podaci_o_autorima/a1:autor">
                                                <xsl:for-each select="a1:zahtev_za_autorska_prava/a1:autorsko_delo/a1:podaci_o_autorima/a1:autor">
                                                    <xsl:if test="a1:pseudonim">
                                                        <xsl:value-of select="a1:pseudonim"/>
                                                    </xsl:if>
                                                </xsl:for-each>
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Punomocnik -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        3) Име, презиме и адреса пуномоћника, ако се пријава подноси преко пуномоћника:
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <xsl:if test="a1:zahtev_za_autorska_prava/a1:punomocnik">
                                <fo:table-row>
                                    <fo:table-cell number-columns-spanned="2" padding="0px 0px 5px 20px">
                                        <fo:block>
                                                    Име и презиме: <xsl:value-of select="//a1:punomocnik//ks:puno_ime//ks:ime"/>
                                                    <xsl:value-of select="//a1:punomocnik//ks:puno_ime//ks:prezime"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                        <fo:table-cell number-columns-spanned="2" padding="0px 0px 15px 20px">
                                            <fo:block>
                                                Адреса:
                                                <xsl:value-of select="//a1:punomocnik//ks:adresa//ks:ulica"/>
                                                <xsl:value-of select="//a1:punomocnik//ks:adresa//ks:broj"/>,
                                                <xsl:value-of select="//a1:punomocnik//ks:adresa//ks:mesto"/>
                                                <xsl:value-of select="//a1:punomocnik//ks:adresa//ks:postanski_broj"/>
                                            </fo:block>
                                        </fo:table-cell>
                                </fo:table-row>
                            </xsl:if>

                            <!-- Naslov dela -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        4) Наслов ауторског дела, односно алтернативни наслов, ако га има, по коме ауторско дело може да се идентификује: <xsl:value-of select="//a1:autorsko_delo//a1:naslov_autorskog_dela"/><br/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Originalno autorsko delo -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        5) Подаци о наслову ауторског дела на коме се заснива дело прераде, ако је у питању ауторско дело прераде, као и податак о аутору изворног дела:
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <xsl:if test="a1:zahtev_za_autorska_prava/a1:autorsko_delo/a1:izvorno_autorsko_delo">
                                <fo:table-row>
                                    <fo:table-cell number-columns-spanned="2" padding="0px 0px 5px 20px">
                                        <fo:block>
                                            Наслов ауторског дела: <xsl:value-of select="//a1:autorsko_delo//a1:izvorno_autorsko_delo//a1:naslov_izvornog_autorskog_dela"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell number-columns-spanned="2" padding="0px 0px 15px 20px">
                                        <fo:block>
                                            Име и презиме аутора: <xsl:value-of select="//a1:autorsko_delo//a1:izvorno_autorsko_delo//a1:autor_izvornog_autorskog_dela//a1:licni_podaci//ks:puno_ime//ks:ime"/>  <xsl:value-of select="//a1:autorsko_delo//a1:izvorno_autorsko_delo//a1:autor_izvornog_autorskog_dela//a1:licni_podaci//ks:puno_ime//ks:prezime"/><br/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:if>
                        </fo:table-body>
                    </fo:table>

                    <fo:table font-family="Calibri, sans-serif" border="1px solid black" border-collapse="collapse">
                        <fo:table-column column-width="70%"/>
                        <fo:table-column column-width="30%"/>
                        <fo:table-body>

                            <!-- Vrsta dela -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        6) Подаци о врсти ауторског дела (књижевно дело, музичко дело, ликовно дело, рачунарски програм и др.): <xsl:value-of select="//a1:autorsko_delo//a1:vrsta_autorskog_dela"/><br/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Forma zapisa dela -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        7) Подаци о форми записа ауторског дела (штампани текст, оптички диск и слично): <xsl:value-of select="//a1:autorsko_delo//a1:forma_zapisa"/><br/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Autori -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        8) Име, адреса и држављанство аутора (групе аутора или коаутора), а ако су у питању један или више аутора који нису живи, имена аутора и године смрти аутора а ако је у питању ауторско дело анонимног аутора навод да је ауторско дело дело анонимног аутора:
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <xsl:for-each select="a1:zahtev_za_autorska_prava/a1:autorsko_delo/a1:podaci_o_autorima/a1:autor">
                                <fo:table-row>
                                    <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 20px">
                                        <fo:block>
                                                Autor: <xsl:value-of select="a1:licni_podaci/ks:puno_ime/ks:ime"/> <xsl:value-of select="a1:licni_podaci//ks:puno_ime//ks:prezime"/>
                                        </fo:block>
                                            <xsl:choose>
                                                    <xsl:when test="a1:godina_smrti">
                                                        <fo:block>Година смрти: <xsl:value-of select="a1:godina_smrti"/></fo:block>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <fo:block>
                                                            Адреса: <xsl:value-of select="a1:licni_podaci/ks:adresa/ks:ulica"/></fo:block>
                                                        <xsl:value-of select="a1:licni_podaci/ks:adresa/ks:broj"/>,
                                                            <xsl:value-of select="a1:licni_podaci/ks:adresa/ks:mesto"/>
                                                            <xsl:value-of select="a1:licni_podaci/ks:adresa/ks:postanski_broj"/><fo:block>
                                                            Тип држаљанства: <xsl:value-of select="a1:licni_podaci/ks:drzavljanstvo/ks:tip_drzavljanstva"/>
                                                            Јмбг: <xsl:value-of select="a1:licni_podaci/ks:drzavljanstvo/ks:jmbg"/>
                                                            Број пасоша: <xsl:value-of select="a1:licni_podaci/ks:drzavljanstvo/ks:broj_pasosa"/>
                                                        </fo:block>
                                                    </xsl:otherwise>
                                                </xsl:choose>
                                                <xsl:if test="a1:anonimni_autor = 'true'">
                                                    <fo:block>Дело анонимног аутора</fo:block>
                                                </xsl:if>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>

                                <!-- Stvoreno u radnom odnosu -->
                                <fo:table-row>
                                    <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                        <fo:block>
                                            9) Податак да ли је у питању ауторско дело створено у радном односу:
                                            <xsl:choose>
                                                <xsl:when test="a1:zahtev_za_autorska_prava/a1:autorsko_delo/a1:stvoreno_u_radnom_odnosu = 'true'">
                                                    Јесте
                                                </xsl:when>
                                                <xsl:otherwise>
                                                    Није
                                                </xsl:otherwise>
                                            </xsl:choose>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                            <!-- Nacin koriscenja -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        10) Начин коришћења ауторског дела или намеравани начин коришћења ауторског дела: <xsl:value-of select="//a1:autorsko_delo//a1:nacin_koriscenja_autorskog_dela"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                        </fo:table-body>
                    </fo:table>

                    <fo:table font-family="Calibri, sans-serif" border="1px solid black" border-collapse="collapse" margin-top="80px">
                        <fo:table-column column-width="70%"/>
                        <fo:table-column column-width="30%"/>
                        <fo:table-body>

                            <!-- Prilozi -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        11) Прилози који се подносе уз захтев:
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 180px">
                                    <fo:block font-size="12" font-weight="bold">
                                        ПОПУЊАВА ЗАВОД:
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="5px 0px 15px 5px">
                                    <fo:block font-weight="bold" padding-bottom="10px">
                                        Прилози уз пријаву:
                                    </fo:block>
                                    <fo:block padding-bottom="10px">
                                        опис ауторског дела (ако је дело поднето на оптичком диску): <xsl:value-of select="//a1:autorsko_delo//a1:opis_autorskog_dela//a1:putanja_do_opisa"/>
                                    </fo:block>
                                    <fo:block padding-bottom="10px">
                                        пример ауторског дела (слика, видео запис, аудио запис): <xsl:value-of select="//a1:autorsko_delo//a1:primer_autorskog_dela//a1:putanja_do_primera"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Prijava -->
                            <fo:table-row>
                                <fo:table-cell column-number="2" border="1px solid black" margin-top="25px">
                                    <fo:block font-size="14" margin="5px 0px 10px 10px">Број пријаве:</fo:block>
                                    <fo:block font-size="14" margin-left="10px" font-weight="bold">A-<xsl:value-of select="//@broj_prijave"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell column-number="2" border="1px solid black" margin-top="25px">
                                    <fo:block font-size="14" margin="5px 0px 10px 10px">Датум подношења:</fo:block>
                                    <fo:block font-size="14" margin-left="10px" font-weight="bold"><xsl:value-of select="//@datum_podnosenja"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
