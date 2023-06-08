<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:z1="http://www.xml.tim14.rs/zahtev_za_priznanje_ziga"
                version="2.0"
>
    <xsl:template match="/">
        <fo:root>
            <xsl:variable name="broj_prijave" select="z1:broj_prijave"/>

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
                            <fo:table-row border-bottom="1px solid black">
                                <fo:table-cell number-columns-spanned="2" padding="30px">
                                    <fo:block font-size="12" font-weight="bold">
                                        РЕШЕЊЕ ЗАХТЕВА ЗА ПРИЗНАЊЕ ЖИГА
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Sluzbenik -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        1) Службеник који је обрадио захтев:
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="0px 0px 5px 15px">
                                    <fo:block>
                                        Име и презиме: <xsl:value-of select="//z1:ime_sluzbenika"/><xsl:value-of select="'&#160;'"/><xsl:value-of select="//z1:prezime_sluzbenika"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Status resenja -->
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="15px 0px 15px 5px">
                                    <fo:block>
                                        2) Статус захтева:
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell number-columns-spanned="2" padding="0px 0px 15px 20px">
                                    <xsl:choose>
                                        <xsl:when test="//z1:odbijen='true'">
                                            <fo:block padding="0px 0px 5px 0px" color="red">
                                                ОДБИЈЕН захтев
                                            </fo:block>
                                            <fo:block>
                                                Разлог одбијања: <xsl:value-of select="//z1:razlog_odbijanja"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block padding="0px 0px 5px 0px" color="green">ПРИХВАЋЕН захтев</fo:block>
                                            <fo:block>
                                                Шифра обрађеног захтева: <xsl:value-of select="//z1:sifra"/>
                                            </fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                            </fo:table-row>

                            <!-- Prijava -->
                            <fo:table-row>
                                <fo:table-cell column-number="2" border="1px solid black" margin-top="25px">
                                    <fo:block font-size="14" margin="5px 0px 10px 10px">Број пријаве:</fo:block>
                                    <fo:block font-size="14" margin-left="10px" font-weight="bold">Ж-<xsl:value-of select="//z1:broj_prijave"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell column-number="2" border="1px solid black" margin-top="25px">
                                    <fo:block font-size="14" margin="5px 0px 10px 10px">Датум обраде:</fo:block>
                                    <fo:block font-size="14" margin-left="10px" font-weight="bold"><xsl:value-of select="format-dateTime(current-dateTime(), '[Y0001]-[M01]-[D01]')"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <fo:table>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell display-align="center" width="100%">
                                    <fo:block-container>
                                        <fo:block>
                                            <fo:external-graphic src="..data/result/{$broj_prijave}.png"
                                                                 width="100%"
                                                                 content-width="scale-to-fit"
                                                                 content-height="scale-to-fit"/>
                                        </fo:block>
                                    </fo:block-container>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
