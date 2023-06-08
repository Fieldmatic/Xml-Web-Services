<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:ks="http://www.xml.tim14.rs/korisnici"
    xmlns:z1="http://www.xml.tim14.rs/zahtev_za_priznanje_ziga"
    exclude-result-prefixes="xs"
    version="2.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Zahtev za priznanje ziga (XSLT)</title>
                <style type="text/css">
                    body {
                    margin: 0;
                    background-color: #242526;
                    }

                    .container {
                    padding-top: 80px;
                    padding-bottom: 65px;
                    margin-left: auto;
                    margin-right: auto;
                    margin-bottom: 6px;
                    text-align: center;
                    font-family: Arial, sans-serif;
                    background-color: white;
                    width: 830px;
                    height: 970px;
                    }

                    h2 {
                    margin-block-end: 0;
                    font-size: 18px;
                    }

                    h3 {
                    font-size: 13px;
                    margin-block-start: 0;
                    margin-block-end: 18px
                    }

                    p {
                    font-size: 13px;
                    margin-block-end: 0;
                    }

                    table {
                    width: 722px;
                    border: 1px solid;
                    margin: 0 auto;
                    border-spacing: 0;
                    font-size: 13px;
                    }
                </style>
            </head>
            <body>
                <xsl:variable name="odabrane_klase" select="z1:zahtev_za_priznanje_ziga/z1:klase_robe_ili_usluge/."/>
                <xsl:variable name="odabrane_klase_string" select="tokenize(string-join($odabrane_klase, '-'), '-')"/>
                <xsl:variable name="osnovna_taksa" select="z1:zahtev_za_priznanje_ziga/z1:takse/z1:osnovna_taksa/."/>
                <xsl:variable name="taksa_za_klase" select="z1:zahtev_za_priznanje_ziga/z1:takse/z1:taksa_za_klase/."/>
                <xsl:variable name="taksa_za_graficko_resenje" select="z1:zahtev_za_priznanje_ziga/z1:takse/z1:taksa_za_graficko_resenje/."/>
                <xsl:variable name="boje_znaka" select="z1:zahtev_za_priznanje_ziga/z1:znak/z1:boje_znaka/."/>
                <xsl:variable name="izgled_znaka" select="z1:zahtev_za_priznanje_ziga/z1:Prilozi_uz_zahtev/z1:primerak_znaka"/>

                <div class="container">
                    <h2>ЗАХТЕВ ЗА ПРИЗНАЊЕ ЖИГА</h2>
                    <h3>Заводу за интелектуалну својину, Кнегиње Љубице 5, 11000 Београд</h3>
                    <p>(попунити на рачунару)</p>
                    <table>
                        <tr>
                            <th style="text-align: left; border-bottom: 1px solid; padding-right: 120px; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px" colspan="3">1. Подносилац пријаве: <span style="font-weight: normal">име и презиме/пословно име, улица и број, поштански број, место, држава пребивалишта/седишта:</span></th>
                        </tr>
                        <tr>
                            <td colspan="3" style="border-bottom: 1px solid; text-align: center; height: 58.5px;">
                                <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:puno_ime">

                                    <xsl:value-of select="concat(z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:puno_ime/ks:ime, ' ', z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:puno_ime/ks:prezime)"/>,
                                </xsl:if>
                                <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:poslovno_ime">
                                    <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:poslovno_ime"/>,
                                </xsl:if>
                                <xsl:value-of select="concat(z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:adresa/ks:ulica, ' ', z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:adresa/ks:broj)"/>,
                                <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:adresa/ks:postanski_broj"/>,
                                <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:adresa/ks:mesto"/>,
                                <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:adresa/ks:drzava"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 33.33%">телефон: <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:broj_mobilnog_telefona"/></td>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 33.33%">e-mail: <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:email"/></td>
                            <td style="padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px">факс: <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:broj_faksa"/></td>
                        </tr>
                    </table>
                    <table style="border-top: none">
                        <tr>
                            <th style="text-align: left; border-bottom: 1px solid; padding-right: 65px; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px" colspan="3">2. Пуномоћник: <span style="font-weight: normal">име и презиме/пословно име, улица и број, поштански број, место, држава пребивалишта/седишта:</span></th>
                        </tr>
                        <tr>
                            <td colspan="3" style="border-bottom: 1px solid; text-align: center; height: 58.5px;">
                                <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:puno_ime">
                                    <xsl:value-of select="concat(z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:puno_ime/ks:ime, ' ', z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:puno_ime/ks:prezime)"/>,
                                </xsl:if>
                                <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:poslovno_ime">
                                    <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:poslovno_ime"/>,
                                </xsl:if>
                                <xsl:value-of select="concat(z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:adresa/ks:ulica, ' ', z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:adresa/ks:broj)"/>,
                                <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:adresa/ks:postanski_broj"/>,
                                <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:adresa/ks:mesto"/>,
                                <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:adresa/ks:drzava"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 33.33%">телефон: <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:broj_mobilnog_telefona"/></td>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 33.33%">e-mail: <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:email"/></td>
                            <td style="padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px">факс: <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:broj_faksa"/></td>
                        </tr>
                    </table>
                    <table style="border-top: none">
                        <tr>
                            <th style="text-align: left; border-bottom: 1px solid; padding-right: 65px; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px" colspan="3">3. Подаци о заједничком представнику ако постоји више подносилаца пријаве:</th>
                        </tr>
                        <tr>
                            <td colspan="3" style="border-bottom: 1px solid; text-align: center; height: 58.5px;">
                                <xsl:if test="z1:zajednicki_predstavnik">
                                    <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:puno_ime">
                                        <xsl:value-of select="concat(z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:puno_ime/ks:ime, ' ', z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:puno_ime/ks:prezime)"/>,
                                    </xsl:if>
                                    <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:poslovno_ime">
                                        <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:poslovno_ime"/>,
                                    </xsl:if>
                                    <xsl:value-of select="concat(z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:adresa/ks:ulica, ' ', z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:adresa/ks:broj)"/>,
                                    <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:adresa/ks:postanski_broj"/>,
                                    <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:adresa/ks:mesto"/>,
                                    <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:adresa/ks:drzava"/>
                                </xsl:if>
                            </td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 33.33%">телефон: <xsl:if test="z1:zajednicki_predstavnik"><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:broj_mobilnog_telefona"/></xsl:if></td>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 33.33%">e-mail: <xsl:if test="z1:zajednicki_predstavnik"><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:email"/></xsl:if></td>
                            <td style="padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px">факс: <xsl:if test="z1:zajednicki_predstavnik"><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:broj_faksa"/></xsl:if></td>
                        </tr>
                    </table>
                    <table style="border-top: none">
                        <tr>
                            <th style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 50%" colspan="3">4. Пријава се подноси за (уписати X):</th>
                            <th style="text-align: left; border-bottom: 1px solid; padding-left: 5.2px; padding-top: 22px; padding-bottom: 2px; width: 50%" rowspan="2">в) изглед знака:</th>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 5%"></td>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 32%">индивидуални жиг</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 2px; padding-bottom: 2px"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:vrsta_ziga='INDUVIDUALNI'">X</xsl:if></td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 5%; font-weight: bold">a)</td>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 32%">колективни жиг</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 2px; padding-bottom: 2px"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:vrsta_ziga='KOLEKTIVNI'">X</xsl:if></td>
                            <td rowspan="16" style="width: 100%; background-position: center; background-repeat: no-repeat; background-size: contain; background-image: url('{$izgled_znaka}')"></td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; border-bottom: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 5%"></td>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 32%">жиг гаранције</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 2px; padding-bottom: 2px"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:vrsta_ziga='ZIG_GARANCIJE'">X</xsl:if></td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 5%"></td>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 32%">вербални знак (знак у речи)</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 2px; padding-bottom: 2px"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka/@vrsta_znaka='VERBALNI'">X</xsl:if></td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 5%"></td>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 32%">графички знак; боју, комбинацију боја</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 2px; padding-bottom: 2px"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka/@vrsta_znaka='GRAFICKI'">X</xsl:if></td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 6%; font-weight: bold">б)</td>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 32%">комбиновани знак</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 2px; padding-bottom: 2px"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka/@vrsta_znaka='KOMBINOVANI'">X</xsl:if></td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 5%"></td>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 32%">тродимензионални знак</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 2px; padding-bottom: 2px"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka/@vrsta_znaka='TRODIMENZIONALNI'">X</xsl:if></td>
                        </tr>
                        <tr>
                            <td style="border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 5%"></td>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; width: 32%">другу врсту знака (навести коју)</td>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px">
                                <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka/@vrsta_znaka='DRUGI'">
                                    <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka"/>
                                </xsl:if>
                            </td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; font-weight:bold" colspan="3">5. Назначење боје, односно боја из којих се знак састоји:</td>
                        </tr>
                        <tr>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 4px; padding-bottom: 4px;" colspan="3"><xsl:value-of select="string-join($boje_znaka, ', ')"/></td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; font-weight:bold" colspan="3">6. Транслитерација знака*:</td>
                        </tr>
                        <tr>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px;" colspan="3">
                                <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:transliteracija_znaka">
                                    <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:znak/z1:transliteracija_znaka"/>
                                </xsl:if>
                                <xsl:if test="not(z1:zahtev_za_priznanje_ziga/z1:znak/z1:transliteracija_znaka)">
                                    /
                                </xsl:if>
                            </td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; font-weight:bold" colspan="3">7. Превод знака*:</td>
                        </tr>
                        <tr>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px;" colspan="3">
                                <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:prevod_znaka">
                                    <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:znak/z1:prevod_znaka"/>
                                </xsl:if>
                                <xsl:if test="not(z1:zahtev_za_priznanje_ziga/z1:znak/z1:prevod_znaka)">
                                    /
                                </xsl:if>
                            </td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px; font-weight:bold" colspan="3">8. Опис знака:</td>
                        </tr>
                        <tr>
                            <td style="border-right: 1px solid; padding-left: 5.2px; padding-top: 4px; padding-bottom: 4px;" colspan="3">
                                <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:znak/z1:opis_znaka"/>
                            </td>
                        </tr>
                    </table>
                    <table style="border-top: none">
                        <tr>
                            <th style="text-align: left; border-bottom: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px;" colspan="23">9. Заокружити бројеве класа робе и услуга према Ничанској класификацији :</th>
                        </tr>
                        <tr>
                            <xsl:for-each select="1 to 23" >
                                <xsl:variable name="broj" select="."/>
                                <xsl:variable name="broj_string" select="format-number($broj, '0')"/>
                                <xsl:if test="$broj = 23">
                                    <td style="text-align: right; border-bottom: 1px solid; padding-right: 5.2px; font-family: 'Times New Roman', serif">
                                        <xsl:if test="$odabrane_klase_string = $broj_string">
                                            <span style="border:1px solid black; border-radius: 100%"><xsl:value-of select="$broj"/></span>
                                        </xsl:if>
                                        <xsl:if test="not($odabrane_klase_string = $broj_string)">
                                            <xsl:value-of select="$broj" />
                                        </xsl:if>
                                    </td>
                                </xsl:if>
                                <xsl:if test="not($broj = 23)">
                                    <td style="border-right: 1px solid; border-bottom: 1px solid; text-align: right; padding-right: 5.2px; font-family: 'Times New Roman', serif">
                                        <xsl:if test="$odabrane_klase_string = $broj_string">
                                            <span style="border:1px solid black; border-radius: 100%"><xsl:value-of select="$broj"/></span>
                                        </xsl:if>
                                        <xsl:if test="not($odabrane_klase_string = $broj_string)">
                                            <xsl:value-of select="$broj" />
                                        </xsl:if>
                                    </td>
                                </xsl:if>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <xsl:for-each select="24 to 45" >
                                <xsl:variable name="broj" select="."/>
                                <xsl:variable name="broj_string" select="format-number($broj, '0')"/>
                                <xsl:if test="$broj = 23">
                                    <td style="text-align: right; padding-right: 5.2px; font-family: 'Times New Roman', serif">
                                        <xsl:if test="$odabrane_klase_string = $broj_string">
                                            <span style="border:1px solid black; border-radius: 100%"><xsl:value-of select="$broj"/></span>
                                        </xsl:if>
                                        <xsl:if test="not($odabrane_klase_string = $broj_string)">
                                            <xsl:value-of select="$broj" />
                                        </xsl:if>
                                    </td>
                                </xsl:if>
                                <xsl:if test="not($broj = 23)">
                                    <td style="border-right: 1px solid; text-align: right; padding-right: 5.2px; font-family: 'Times New Roman', serif">
                                        <xsl:if test="$odabrane_klase_string = $broj_string">
                                            <span style="border:1px solid black; border-radius: 100%"><xsl:value-of select="$broj"/></span>
                                        </xsl:if>
                                        <xsl:if test="not($odabrane_klase_string = $broj_string)">
                                            <xsl:value-of select="$broj" />
                                        </xsl:if>
                                    </td>
                                </xsl:if>
                            </xsl:for-each>
                            <td></td>
                        </tr>
                    </table>
                    <table style="border-top: none">
                        <tr>
                            <th style="text-align: left; padding-left: 5.2px; padding-top: 2px; padding-bottom: 2px;">10. Затражено право првенства и основ:</th>
                        </tr>
                        <tr>
                            <td style="text-align: center; padding-top: 2px; padding-bottom: 2px;">
                                <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:pravo_prvenstva/@zatrazeno_pravo_prvenstva">
                                    <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:pravo_prvenstva/z1:osnov_prava_prvenstva/."/>
                                </xsl:if>
                                <xsl:if test="not(z1:zahtev_za_priznanje_ziga/z1:pravo_prvenstva/@zatrazeno_pravo_prvenstva)">
                                    /
                                </xsl:if>
                            </td>
                        </tr>
                    </table>
                    <div style="width: 722px; margin-left: auto; margin-right: auto; padding: 0;">
                        <p style="text-align: left; padding-left: 5.2px; padding-top: 4px;">*Попунити само ако је знак или елемент знака исписан словима која нису ћирилична или латинична</p>
                    </div>
                </div>
                <div class="container">
                    <table>
                        <tr>
                            <th style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; width: 40%">11. Плаћене таксе:</th>
                            <th style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; width: 12%">Динара</th>
                            <th style="text-align: center; padding-top: 2px; padding-bottom: 16px;">Потпис подносиоца захтева</th>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 16px; padding-bottom: 2px; font-weight: bold">а) основна такса</td>
                            <td style="text-align: right; border-bottom: 1px solid; border-right: 1px solid; padding-right: 5.2px; padding-top: 16px; padding-bottom: 2px;"><xsl:value-of select="$osnovna_taksa"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-right: 1px solid; padding-left: 5.2px; padding-top: 16px; padding-bottom: 2px; font-weight: bold">б) за <xsl:value-of select="count($odabrane_klase)"/> класа</td>
                            <td style="text-align: right; border-right: 1px solid; padding-right: 5.2px; padding-top: 16px; padding-bottom: 2px;"><xsl:value-of select="$taksa_za_klase"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 16px; padding-bottom: 2px; font-weight: bold">в) за графичко решење</td>
                            <td style="text-align: right; border-bottom: 1px solid; border-right: 1px solid; padding-right: 5.2px; padding-top: 16px; padding-bottom: 2px;"><xsl:value-of select="$taksa_za_graficko_resenje"/></td>
                            <td style="text-align: left; padding-left: 5.2px; padding-top: 16px; padding-bottom: 2px; font-style: italic; font-size: 13px;">* Печат, уколико је потребан у складу са законом</td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-right: 1px solid; padding-left: 5.2px; padding-top: 16px; padding-bottom: 4px; font-weight: bold">УКУПНО</td>
                            <td style="text-align: right; border-right: 1px solid; padding-right: 5.2px; padding-top: 16px; padding-bottom: 4px;"><xsl:value-of select="$osnovna_taksa + $taksa_za_graficko_resenje + $taksa_za_klase"/></td>
                            <td></td>
                        </tr>
                    </table>
                    <table style="margin-top: 36px">
                        <tr>
                            <th style="text-align: center; border-bottom: 1px solid; padding-top: 16px; padding-bottom: 2px;" colspan="3">ПОПУЊАВА ЗАВОД</th>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; font-weight: bold" colspan="2">Прилози уз захтев:</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; width: 47%;">Примерак знака</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 9px; padding-bottom: 9px; width: 10%;"></td>
                            <td style="text-align: center; padding-top: 16px; padding-bottom: 2px;">Број пријаве жига:</td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; width: 47%;">Списак робе и услуга**</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 9px; padding-bottom: 9px; width: 10%;"></td>
                            <td style="text-align: center; padding-top: 16px; padding-bottom: 2px;">Ж-<xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:prijava/z1:broj_prijave"/>/<xsl:value-of select="year-from-date(z1:zahtev_za_priznanje_ziga/z1:prijava/z1:datum_podnosenja)"/></td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; width: 47%;">Пуномоћје</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 9px; padding-bottom: 9px; width: 10%;"></td>
                            <td style="text-align: center; padding-top: 16px; padding-bottom: 2px; font-weight:bold;">Датум подношења:</td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; width: 47%;">Генерално пуномоћје раније приложено</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 9px; padding-bottom: 9px; width: 10%;"></td>
                            <td style="text-align: center; padding-top: 16px; padding-bottom: 2px; font-weight:bold;"><xsl:value-of select="format-date(z1:zahtev_za_priznanje_ziga/z1:prijava/z1:datum_podnosenja, '[D1].[M1].[Y1].')"/></td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; width: 47%;">Пуномоћје ће бити накнадно достављено</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 9px; padding-bottom: 9px; width: 10%;"></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; width: 47%;">Општи акт о колективном жигу/жигу гаранције</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 9px; padding-bottom: 9px; width: 10%;"></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-bottom: 1px solid; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; width: 47%;">Доказ о праву првенства</td>
                            <td style="text-align: center; border-bottom: 1px solid; border-right: 1px solid; padding-top: 9px; padding-bottom: 9px; width: 10%;"></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td style="text-align: left; border-right: 1px solid; padding-left: 5.2px; padding-top: 2px; padding-bottom: 16px; width: 47%;">Доказ о уплати таксе</td>
                            <td style="text-align: center; border-right: 1px solid; padding-top: 9px; padding-bottom: 9px; width: 10%;"></td>
                            <td></td>
                        </tr>
                    </table>
                    <div style="width: 722px; margin-left: auto; margin-right: auto; padding: 0;">
                        <p style="text-align: justify; padding-left: 5.2px; padding-right: 5.2px; padding-top: 100px;">**Уз заокруживање броја класе робе/услуга Ничанске класификације у рубрици 9 доставља се и списак који
                            садржи конкретне називе робе коју подносилац пријаве производи, односно услуга које пружа. У циљу
                            одређења обима заштите која се стиче жигом, списак треба да садржи јасне и прецизне називе робе и
                            услуга. У ту сврху могу се користити појмови из детаљне Листе роба и услуга или MGS Manager апликације,
                            доступних на сајту Завода. Уколико се у списак уносе термини из Листе класа Ничанске класификације,
                            заштита обухвата само тако именоване, конкретне робе/услуге у њиховом јасном и недвосмисленом
                            значењу.</p>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>