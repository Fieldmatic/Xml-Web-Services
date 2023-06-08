<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:fox="http://xmlgraphics.apache.org/fop/extensions"
                xmlns:ks="http://www.xml.tim14.rs/korisnici"
                xmlns:z1="http://www.xml.tim14.rs/zahtev_za_priznanje_ziga"
                version="2.0">
    <xsl:template match="/">
        <fo:root>
            <xsl:variable name="odabrane_klase" select="z1:zahtev_za_priznanje_ziga/z1:klase_robe_ili_usluge/."/>
            <xsl:variable name="odabrane_klase_string" select="tokenize(string-join($odabrane_klase, '-'), '-')"/>
            <xsl:variable name="osnovna_taksa" select="z1:zahtev_za_priznanje_ziga/z1:takse/z1:osnovna_taksa/."/>
            <xsl:variable name="taksa_za_klase" select="z1:zahtev_za_priznanje_ziga/z1:takse/z1:taksa_za_klase/."/>
            <xsl:variable name="taksa_za_graficko_resenje" select="z1:zahtev_za_priznanje_ziga/z1:takse/z1:taksa_za_graficko_resenje/."/>
            <xsl:variable name="boje_znaka" select="z1:zahtev_za_priznanje_ziga/z1:znak/z1:boje_znaka/."/>
            <xsl:variable name="iz" select="z1:zahtev_za_priznanje_ziga/z1:Prilozi_uz_zahtev/z1:primerak_znaka"/>
            <xsl:variable name="split_parts" select="tokenize($iz, 'src')" />
            <xsl:variable name="izgled_znaka" select="concat('../src', $split_parts[2])" />

            <fo:layout-master-set>
                <fo:simple-page-master master-name="z1-page">
                    <fo:region-body margin-top="72px" margin-right="39px" margin-left="39px" margin-bottom="60px"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="z1-page">
                <fo:flow flow-name="xsl-region-body" font-family="Arial, sans-serif">
                    <!--   NASLOV   -->
                    <fo:block font-size="14px" font-weight="bold" text-align="center">
                        ЗАХТЕВ ЗА ПРИЗНАЊЕ ЖИГА
                    </fo:block>
                    <fo:block font-size="10px" font-weight="bold" text-align="center">
                        Заводу за интелектуалну својину, Кнегиње Љубице 5, 11000 Београд
                    </fo:block>
                    <fo:block font-size="10px" text-align="center" margin-top="11px">
                        (попунити на рачунару)
                    </fo:block>
                    <!--   PODNOSILAC PRIJAVE   -->
                    <fo:table border="0.5px solid" font-size="10px">
                        <fo:table-column column-width="33.33%"/>
                        <fo:table-column column-width="33.33%"/>
                        <fo:table-column column-width="33.33%"/>
                        <fo:table-body>
                            <fo:table-row border-bottom="0.5px solid">
                                <fo:table-cell number-columns-spanned="3">
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="1px" margin-right="80px">1. Подносилац пријаве: <fo:inline font-weight="normal">име и презиме/пословно име, улица и број, поштански број, место, држава пребивалишта/седишта:</fo:inline></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row border-bottom="0.5px solid">
                                <fo:table-cell number-columns-spanned="3">
                                    <fo:block padding-top="18" padding-bottom="18" text-align="center">
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
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid" margin-left="5px">
                                    <fo:block>телефон: <fo:inline><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:broj_mobilnog_telefona"/></fo:inline></fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" margin-left="5px">
                                    <fo:block>e-mail: <fo:inline><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:email"/></fo:inline></fo:block>
                                </fo:table-cell>
                                <fo:table-cell margin-left="5px">
                                    <fo:block>факс: <fo:inline><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:podnosilac/ks:broj_faksa"/></fo:inline></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <!--   PUNOMOCNIK   -->
                    <fo:table border-left="0.5px solid" border-right="0.5px solid" border-bottom="0.5px solid" font-size="10px">
                        <fo:table-column column-width="33.33%"/>
                        <fo:table-column column-width="33.33%"/>
                        <fo:table-column column-width="33.33%"/>
                        <fo:table-body>
                            <fo:table-row border-bottom="0.5px solid">
                                <fo:table-cell number-columns-spanned="3">
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="1px" margin-right="80px">2. Пуномоћник: <fo:inline font-weight="normal">име и презиме/пословно име, улица и број, поштански број, место, држава пребивалишта/седишта:</fo:inline></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row border-bottom="0.5px solid">
                                <fo:table-cell number-columns-spanned="3">
                                    <fo:block padding-top="18" padding-bottom="18" text-align="center">
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
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid" margin-left="5px">
                                    <fo:block>телефон: <fo:inline><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:broj_mobilnog_telefona"/></fo:inline></fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" margin-left="5px">
                                    <fo:block>e-mail: <fo:inline><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:email"/></fo:inline></fo:block>
                                </fo:table-cell>
                                <fo:table-cell margin-left="5px">
                                    <fo:block>факс: <fo:inline><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:punomocnik/ks:broj_faksa"/></fo:inline></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <!--   ZAJEDNICKI PREDSTAVNIK   -->
                    <fo:table border-left="0.5px solid" border-right="0.5px solid" border-bottom="0.5px solid" font-size="10px">
                        <fo:table-column column-width="33.33%"/>
                        <fo:table-column column-width="33.33%"/>
                        <fo:table-column column-width="33.33%"/>
                        <fo:table-body>
                            <fo:table-row border-bottom="0.5px solid">
                                <fo:table-cell number-columns-spanned="3">
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="1px" margin-right="80px">3. Подаци о заједничком представнику ако постоји више подносилаца пријаве:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row border-bottom="0.5px solid">
                                <fo:table-cell number-columns-spanned="3">
                                    <fo:block padding-top="18" padding-bottom="18" text-align="center">
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
                                        <xsl:if test="not(z1:zajednicki_predstavnik)">
                                            /
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid" margin-left="5px">
                                    <fo:block>телефон: <fo:inline><xsl:if test="z1:zajednicki_predstavnik"><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:broj_mobilnog_telefona"/></xsl:if></fo:inline></fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" margin-left="5px">
                                    <fo:block>e-mail: <fo:inline><xsl:if test="z1:zajednicki_predstavnik"><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:email"/></xsl:if></fo:inline></fo:block>
                                </fo:table-cell>
                                <fo:table-cell margin-left="5px">
                                    <fo:block>факс: <fo:inline><xsl:if test="z1:zajednicki_predstavnik"><xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:zajednicki_predstavnik/ks:broj_faksa"/></xsl:if></fo:inline></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <!--   PODACI O ZIGU I ZNAKU   -->
                    <fo:table border="0.5px solid" font-size="10px">
                        <fo:table-column column-width="5%"/>
                        <fo:table-column column-width="34%"/>
                        <fo:table-column column-width="11%"/>
                        <fo:table-column column-width="50%"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="3" border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="1px">4. Пријава се подноси за (уписати X):</fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <!--   VRSTA ZIGA   -->
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-left="5px" margin-top="1px">индивидуални жиг</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block margin-top="1px" text-align="center"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:vrsta_ziga='INDUVIDUALNI'">X</xsl:if></fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid">
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="1px">в) изглед знака:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid">
                                    <fo:block margin-left="5px" font-weight="bold">a)</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-left="5px" margin-top="1px">колективни жиг</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block margin-top="1px" text-align="center"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:vrsta_ziga='KOLEKTIVNI'">X</xsl:if></fo:block>
                                </fo:table-cell>
                                <fo:table-cell display-align="center" width="100%" number-rows-spanned="15">
                                    <fo:block-container>
                                        <fo:block>
                                            <fo:external-graphic src="{$izgled_znaka}"
                                                                 width="100%"
                                                                 content-width="scale-to-fit"
                                                                 content-height="scale-to-fit"/>
                                        </fo:block>
                                    </fo:block-container>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-left="5px" margin-top="1px">жиг гаранције</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block margin-top="1px" text-align="center"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:vrsta_ziga='ZIG_GARANCIJE'">X</xsl:if></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <!--   VRSTA ZNAKA   -->
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-left="5px" margin-top="1px">вербални знак (знак у речи)</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block margin-top="1px" text-align="center"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka/@vrsta_znaka='VERBALNI'">X</xsl:if></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-left="5px" margin-top="1px">графички знак; боју, комбинацију боја</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block margin-top="1px" text-align="center"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka/@vrsta_znaka='GRAFICKI'">X</xsl:if></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid">
                                    <fo:block margin-left="5px" font-weight="bold">б)</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-left="5px" margin-top="1px">комбиновани знак</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block margin-top="1px" text-align="center"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka/@vrsta_znaka='KOMBINOVANI'">X</xsl:if></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-left="5px" margin-top="1px">тродимензионални знак</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block margin-top="1px" text-align="center"><xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka/@vrsta_znaka='TRODIMENZIONALNI'">X</xsl:if></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-left="5px" margin-top="1px">другу врсту знака (навести коју)</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block margin-top="1px" text-align="center">
                                        <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka/@vrsta_znaka='DRUGI'">
                                            <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:znak/z1:vrsta_znaka"/>
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <!--   BOJE ZNAKA   -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="3" border-right="0.5px solid">
                                    <fo:block margin-left="5px" font-weight="bold">5. Назначење боје, односно боја из којих се знак састоји:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="3" border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block padding-top="4px" padding-bottom="4px" text-align="center"><xsl:value-of select="string-join($boje_znaka, ', ')"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <!--   TRANSLITERACIJA ZNAKA   -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="3" border-right="0.5px solid">
                                    <fo:block margin-left="5px" font-weight="bold">6. Транслитерација знака*:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="3" border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block text-align="center">
                                        <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:transliteracija_znaka">
                                            <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:znak/z1:transliteracija_znaka"/>
                                        </xsl:if>
                                        <xsl:if test="not(z1:zahtev_za_priznanje_ziga/z1:znak/z1:transliteracija_znaka)">
                                            /
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <!--   PREVOD ZNAKA   -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="3" border-right="0.5px solid">
                                    <fo:block margin-left="5px" font-weight="bold">7. Превод знака*:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="3" border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block text-align="center">
                                        <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:znak/z1:prevod_znaka">
                                            <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:znak/z1:prevod_znaka"/>
                                        </xsl:if>
                                        <xsl:if test="not(z1:zahtev_za_priznanje_ziga/z1:znak/z1:prevod_znaka)">
                                            /
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <!--   OPIS ZNAKA   -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="3" border-right="0.5px solid">
                                    <fo:block margin-left="5px" font-weight="bold">8. Опис знака:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="3" border-right="0.5px solid">
                                    <fo:block padding-top="4px" padding-bottom="4px" text-align="center">
                                        <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:znak/z1:opis_znaka"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <!--   KLASE ROBE I USLUGA   -->
                    <fo:table border-left="0.5px solid" border-right="0.5px solid" border-bottom="0.5px solid" font-size="10px">
                        <xsl:for-each select="1 to 23">
                            <fo:table-column column-width="4.3478%"/>
                        </xsl:for-each>
                        <fo:table-body>
                            <fo:table-row border-bottom="0.5px solid">
                                <fo:table-cell number-columns-spanned="23">
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="1px">9. Заокружити бројеве класа робе и услуга према Ничанској класификацији :</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row id="polje_klasa_robe" border-bottom="0.5px solid" font-family="Times New Roman, serif">
                                <xsl:for-each select="1 to 23" >
                                    <xsl:variable name="broj" select="."/>
                                    <xsl:variable name="broj_string" select="format-number($broj, '0')"/>
                                    <xsl:if test="$broj = 23">
                                        <fo:table-cell text-align="right" margin-right="5px" margin-left="5px">
                                            <xsl:if test="$odabrane_klase_string = $broj_string">
                                                <fo:block border="1px solid black" fox:border-radius="100%"><xsl:value-of select="$broj" /></fo:block>
                                            </xsl:if>
                                            <xsl:if test="not($odabrane_klase_string = $broj_string)">
                                                <fo:block><xsl:value-of select="$broj" /></fo:block>
                                            </xsl:if>
                                        </fo:table-cell>
                                    </xsl:if>
                                    <xsl:if test="not($broj = 23)">
                                        <fo:table-cell border-right="0.5px solid black" text-align="right" margin-right="5px" margin-left="5px">
                                            <xsl:if test="$odabrane_klase_string = $broj_string">
                                                <fo:block border="1px solid black" fox:border-radius="100%"><xsl:value-of select="$broj" /></fo:block>
                                            </xsl:if>
                                            <xsl:if test="not($odabrane_klase_string = $broj_string)">
                                                <fo:block><xsl:value-of select="$broj" /></fo:block>
                                            </xsl:if>
                                        </fo:table-cell>
                                    </xsl:if>
                                </xsl:for-each>
                            </fo:table-row>
                            <fo:table-row border-bottom="0.5px solid" font-family="Times New Roman, serif">
                                <xsl:for-each select="24 to 45">
                                    <xsl:variable name="broj" select="."/>
                                    <xsl:variable name="broj_string" select="format-number($broj, '0')"/>
                                    <fo:table-cell border-right="0.5px solid" text-align="right" margin-right="5px" margin-left="5px">
                                        <xsl:if test="$odabrane_klase_string = $broj_string">
                                            <fo:block border="1px solid black" fox:border-radius="100%"><xsl:value-of select="$broj" /></fo:block>
                                        </xsl:if>
                                        <xsl:if test="not($odabrane_klase_string = $broj_string)">
                                            <fo:block><xsl:value-of select="$broj" /></fo:block>
                                        </xsl:if>
                                    </fo:table-cell>
                                </xsl:for-each>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <!--   PRAVO PRVENSTVA   -->
                    <fo:table border-left="0.5px solid" border-right="0.5px solid" border-bottom="0.5px solid" font-size="10px">
                        <fo:table-column column-width="100%"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="1px">10. Затражено право првенства и основ:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block padding-top="3px" padding-bottom="3px" text-align="center">
                                        <xsl:if test="z1:zahtev_za_priznanje_ziga/z1:pravo_prvenstva/@zatrazeno_pravo_prvenstva">
                                            <xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:pravo_prvenstva/z1:osnov_prava_prvenstva/."/>
                                        </xsl:if>
                                        <xsl:if test="not(z1:zahtev_za_priznanje_ziga/z1:pravo_prvenstva/@zatrazeno_pravo_prvenstva)">
                                            Није затражено право првенства.
                                        </xsl:if>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <!--   FUSNOTA *   -->
                    <fo:block font-size="10px" margin-top="15px" margin-left="5px">*Попунити само ако је знак или елемент знака исписан словима која нису ћирилична или латинична
                    </fo:block>
                    <!--   TAKSE   -->
                    <fo:table border="0.5px solid" font-size="10px">
                        <fo:table-column column-width="40%"/>
                        <fo:table-column column-width="12%"/>
                        <fo:table-column column-width="48%"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid">
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="1px" margin-bottom="11px">11. Плаћене таксе:</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid">
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="1px" margin-bottom="11px">Динара</fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block font-weight="bold" text-align="center" margin-top="1px" margin-bottom="11px">Потпис подносиоца захтева</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid" border-top="0.5px solid">
                                    <fo:block font-weight="bold" margin-left="5px" margin-bottom="1px" margin-top="11px">а) основна такса</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid" border-top="0.5px solid">
                                    <fo:block margin-right="5px" text-align="right" margin-bottom="1px" margin-top="11px"><xsl:value-of select="$osnovna_taksa"/></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid">
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="11px">б) за <fo:inline><xsl:value-of select="count($odabrane_klase)"/></fo:inline> класа</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid">
                                    <fo:block margin-right="5px" text-align="right" margin-top="11px"><xsl:value-of select="$taksa_za_klase"/></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block font-weight="bold" margin-left="5px" margin-top="11px">в) за графичко решење</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block margin-right="5px" text-align="right" margin-top="11px"><xsl:value-of select="$taksa_za_graficko_resenje"/></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block font-size="9px" margin-left="5px" font-style="italic" margin-top="11px">* Печат, уколико је потребан у складу са законом</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block font-weight="bold" margin-left="5px" margin-bottom="2px" margin-top="13px">УКУПНО</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="0.5px solid" border-bottom="0.5px solid">
                                    <fo:block margin-right="5px" margin-bottom="2px" text-align="right" margin-top="13px"><xsl:value-of select="$taksa_za_graficko_resenje + $taksa_za_klase + $osnovna_taksa"/></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <!--   PRILOZI   -->
                    <fo:table margin-top="22px" border="0.5px solid" font-size="10px">
                        <fo:table-column column-width="47%"/>
                        <fo:table-column column-width="10%"/>
                        <fo:table-column column-width="43%"/>
                        <fo:table-body>
                            <fo:table-row border-bottom="0.5px solid">
                                <fo:table-cell number-columns-spanned="3">
                                    <fo:block text-align="center" font-weight="bold" margin-top="11px">ПОПУЊАВА ЗАВОД</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid" number-columns-spanned="2">
                                    <fo:block font-weight="bold" margin-bottom="11px" margin-left="5px" margin-top="1px">Прилози уз захтев:</fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-bottom="11px" margin-left="5px" margin-top="1px">Примерак знака</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block text-align="center" margin-top="11px">Број пријаве жига:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-bottom="11px" margin-left="5px" margin-top="1px">Списак робе и услуга**</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block text-align="center" margin-top="11px">Ж-<xsl:value-of select="z1:zahtev_za_priznanje_ziga/z1:prijava/z1:broj_prijave"/>/<xsl:value-of select="year-from-date(z1:zahtev_za_priznanje_ziga/z1:prijava/z1:datum_podnosenja)"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-bottom="11px" margin-left="5px" margin-top="1px">Пуномоћје</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block font-weight="bold" text-align="center" margin-top="11px">Датум подношења:</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-bottom="11px" margin-left="5px" margin-top="1px">Генерално пуномоћје раније приложено</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block font-weight="bold" text-align="center" margin-top="11px"><xsl:value-of select="format-date(z1:zahtev_za_priznanje_ziga/z1:prijava/z1:datum_podnosenja, '[D1].[M1].[Y1].')"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-bottom="11px" margin-left="5px" margin-top="1px">Пуномоћје ће бити накнадно достављено</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-bottom="11px" margin-left="5px" margin-top="1px">Општи акт о колективном жигу/жигу гаранције</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-bottom="11px" margin-left="5px" margin-top="1px">Доказ о праву првенства</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block margin-bottom="11px" margin-left="5px" margin-top="1px">Доказ о уплати таксе</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-bottom="0.5px solid" border-right="0.5px solid">
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <!--   FUSNOTA **   -->
                    <fo:block font-size="10px" margin-top="75px" text-align="justify" margin-left="5px">**Уз заокруживање броја класе робе/услуга Ничанске класификације у рубрици 9 доставља се и списак који
                        садржи конкретне називе робе коју подносилац пријаве производи, односно услуга које пружа. У циљу
                        одређења обима заштите која се стиче жигом, списак треба да садржи јасне и прецизне називе робе и
                        услуга. У ту сврху могу се користити појмови из детаљне Листе роба и услуга или MGS Manager апликације,
                        доступних на сајту Завода. Уколико се у списак уносе термини из Листе класа Ничанске класификације,
                        заштита обухвата само тако именоване, конкретне робе/услуге у њиховом јасном и недвосмисленом
                        значењу.
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>